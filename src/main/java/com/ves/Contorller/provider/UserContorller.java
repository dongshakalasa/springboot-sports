package com.ves.Contorller.provider;

import com.ves.Mapper.UserMapper;
import com.ves.Service.User.UserService;
import com.ves.pojo.Address;
import com.ves.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserContorller {

    @Autowired
    UserService userService;
    @Autowired
    Email email;

    /**
     * 注册验证码
     * @param account
     * @param session
     * @return
     */
    @GetMapping("/sendCode")
    public Result getCodeRegister(@RequestParam String account, HttpSession session){
        Boolean flag = userService.judgeAccount(account);
        if(flag){
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
            return new Result(201,null,"当前账号已注册！");
        }
    }

    /**
     * 注册
     * @param userInfo
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody Map<String,Object> userInfo, HttpSession session){
        /**
         * 整理数据
         */
        //传递的参数
        String account = (String) userInfo.get("account");
        String password = (String) userInfo.get("password");
        String code = (String) userInfo.get("code");
        //默认参数
        String imgUrl = "http://vescow.pannifeng.top/sports/user.webp";
        //获取服务器参数并删除
        String code1 = (String) session.getAttribute(account);
        session.removeAttribute(account);

        try {
            //判断验证码是否错误
            if(code.equals(code1)){
                //判断是否存在此账号
                Boolean flag = userService.judgeAccount(account);
                if(flag) {
                    //整理存储参数
                    String uuid = UUid.getUUid();
                    String pswd = EncoderMd5.Md5(password);
                    System.out.println(account+pswd+uuid+imgUrl+account);
                    userService.createUser(account,pswd,uuid,imgUrl,account);
                    return new Result(200,null,"注册成功！");
                }else {
                    return new Result(201,null,"账号已存在");
                }
            }else {
                return new Result(201,null,"验证码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(204,null,"服务器异常！");
        }
    }

    /**
     * 账号登录
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/login")
    public Result userAccountLogin(@RequestBody Map<String,Object> user, HttpSession session, HttpServletRequest request){
        //判断是否已经登录
        String token = request.getHeader("TOKEN");
        if(session.getAttribute(token) != null) {
            return new Result(201,null,"账号已登录");
        }
        //解析接收的数据
        String password = (String) user.get("password");
        String account = (String) user.get("account");
        //产生实时uuid
        String uuid = UUid.getUUid();
        try{
            //查询用户密码
            String pswd = userService.getPswd(account);
            //判断账号是否错误
            if(pswd == null) {
                return new Result(201,null,"账号错误");
            }
            //比照用户密码
            if(EncoderMd5.Md5(password).equals(pswd)){
                //成功
                String userUUid = userService.getUuid(account);
                session.setAttribute(uuid,userUUid);
                session.setMaxInactiveInterval(60*60*24);
                return new Result(Code.REQUEST_OK,uuid,"登录成功！");
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
        if(userUUid != null){
            return new Result(Code.REQUEST_OK,"ok",Message.REQUEST_OK);
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

    /**
     * 获取用户信息
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/userInfo")
    public Result userInfo(HttpSession session,HttpServletRequest request) {
        String uuid = request.getHeader("token");
        String userUuid = (String) session.getAttribute(uuid);
        Map<String, Object> userInfo = userService.getUserInfo(userUuid);
        return new Result(200,userInfo,"ok");
    }

    /**
     * 获取用户所有地址信息
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/userAddress")
    public Result userAddressList(HttpSession session,HttpServletRequest request) {
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);
        List<Object> returnList = new ArrayList<>();
        List<Address> userAddress = userService.getUserAddress(userUUid);
        for(Address a : userAddress) {
            Map<String,Object> user = new HashMap<>();
            String address = a.getProvince()+a.getCity()+a.getCounty()+a.getAddressDetail();
            user.put("id",a.getId());
            user.put("name",a.getName());
            user.put("tel",a.getTel());
            user.put("address",address);
            user.put("isDefault",a.getIsDefault());
            returnList.add(user);
        }
        return new Result(200,returnList,"ok");
    }

    /**
     * 获取用户地址信息
     * @param id
     * @return
     */
    @GetMapping("/userAddressInfo/{id}")
    public Result userAddressInfo(@PathVariable int id) {
        Address userAddressInfo = userService.getUserAddressInfo(id);
        return new Result(200,userAddressInfo,"ok");
    }

    /**
     * 修改地址信息
     * @param userAddressInfo
     * @return
     */
    @PostMapping("/updateAddress")
    public Result updateAddressInfo(@RequestBody Map<String,Object> userAddressInfo) {
        int flag = userService.updateAddressInfo(userAddressInfo);
        if(flag > 0) {
            return new Result(200,null,"ok");
        }
        return new Result(201,null,"error");
    }

    /**
     * 删除地址
     * @param id
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/deleteAddress/{id}")
    public Result deleteAddress(@PathVariable int id,HttpSession session,HttpServletRequest request) {
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);
        if(userUUid != null) {
            int flag = userService.deleteAddress(id);
            if(flag > 0) {
                return new Result(200,null,"ok");
            }else {
                return new Result(201,null,"error");
            }
        }else {
            return new Result(202,null,"请登录！");
        }
    }
}
