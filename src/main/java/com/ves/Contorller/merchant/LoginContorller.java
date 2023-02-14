package com.ves.Contorller.merchant;

import com.ves.Service.merchant.LoginService;
import com.ves.pojo.Merchant;
import com.ves.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/merchant")
public class LoginContorller {

    @Autowired
    Email email;
    @Autowired
    LoginService loginService;

    /**
     * 验证码
     * @param account
     * @param session
     * @return
     */
    @GetMapping("/sendCode")
    public Result getCodeRegister(@RequestParam String account, HttpSession session){
        Boolean flag = loginService.judgeAccountMerChant(account);
        if(!flag){
            String code = email.getCode();
            Boolean aBoolean = email.sendSimpleMail(code, account);
            if(aBoolean) {
                session.setAttribute(account,code);
                session.setMaxInactiveInterval(60*10);
                return new Result(200, null, "符合条件！");
            }else {
                return new Result(201, null, "验证码获取失败！");
            }
        }else{
            return new Result(201,null,"当前账号暂未注册！");
        }
    }

    /**
     * 验证码登录
     * @param session
     * @param request
     * @return
     */
    @PostMapping("/login/code")
    public Result userCodeLogin(@RequestBody Map<String,Object> userInfo, HttpSession session, HttpServletRequest request) {
        String account = (String) userInfo.get("account");
        String code = (String) userInfo.get("code");
        String code1 = (String) session.getAttribute(account);
        session.removeAttribute(account);

        //产生实时uuid
        String uuid = UUid.getUUid();
        if(code.equals(code1)) {
            //查询用户密码
            String pswd = loginService.getPswd(account);
            //判断账号是否错误
            if(pswd == null) {
                return new Result(201,null,"账号错误");
            }else {
                //获取uuid
                Map<String,Object> map = new HashMap<>();
                String userUUid = loginService.getUuid(account);
                session.setAttribute(uuid,userUUid);
                session.setMaxInactiveInterval(60*60*24);
                map.put("uuidToken",uuid);
                return new Result(Code.REQUEST_OK,map,"登录成功！");
            }
        }else {
            return new Result(201,null,"验证码错误！");
        }
    }

    /**
     * 账号登录
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/login/account")
    public Result userAccountLogin(@RequestBody Map<String,Object> user, HttpSession session,HttpServletRequest request){
        //解析接收的数据
        String password = (String) user.get("password");
        String account = (String) user.get("account");
        //产生实时uuid
        String uuid = UUid.getUUid();
        try{
            //查询用户密码
            String pswd = loginService.getPswd(account);
            //判断账号是否错误
            if(pswd == null) {
                return new Result(201,null,"账号错误");
            }
            //比照用户密码
            if(EncoderMd5.Md5(password).equals(pswd)){
                //成功
                Map<String,Object> map = new HashMap<>();
                String userUUid = loginService.getUuid(account);
                session.setAttribute(uuid,userUUid);
                session.setMaxInactiveInterval(60*60*24);
                map.put("uuidToken",uuid);
                return new Result(Code.REQUEST_OK,map,"登录成功！");
            }else {
                return new Result(Code.REQUEST_ERR,null,"密码错误！");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return new Result(201,null,"服务器出错了！");
        }
    }

    /**
     * 自动登录
     */
    @GetMapping("/authLogin")
    public Result userAuthLogin(HttpServletRequest request,HttpSession session){
        String uuid = request.getHeader("token");
        String userUUid = (String) session.getAttribute(uuid);
        //System.out.println("自动登录，账户："+userUUid);
        //System.out.println("自动登录，session："+uuid);
        Merchant merchantInfo = loginService.getMerchantInfo(userUUid);
        if(userUUid != null){
            return new Result(Code.REQUEST_OK,merchantInfo,Message.REQUEST_OK);
        }
        return new Result(201,null,"登录超时！");
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public Result userLogout(HttpServletRequest request,HttpSession session){
        String uuid = request.getHeader("token");
        session.removeAttribute(uuid);
        return new Result(Code.REQUEST_OK,null, Message.REQUEST_OK);
    }
}
