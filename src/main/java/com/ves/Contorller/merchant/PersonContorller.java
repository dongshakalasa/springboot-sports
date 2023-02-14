package com.ves.Contorller.merchant;

import com.ves.Service.merchant.PersonService;
import com.ves.pojo.Merchant;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/merchant/person")
public class PersonContorller {

    @Autowired
    PersonService personService;

    @GetMapping("/info")
    public Result selectInfo(HttpServletRequest request, HttpSession session){
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);

        Merchant merchant = personService.selectMerchantInfo(userUUid);
        return new Result(200,merchant,"ok");
    }
}
