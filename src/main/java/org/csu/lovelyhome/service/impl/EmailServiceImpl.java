package org.csu.lovelyhome.service.impl;

import org.csu.lovelyhome.entity.House;
import org.csu.lovelyhome.entity.User;
import org.csu.lovelyhome.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.mail.HtmlEmail;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private HouseServiceImpl houseService;

    @Override
    public void houseOutToUser(int userId, int houseId) {
        User user = userService.getById(userId);
        House house = houseService.getById(houseId);
        String emailAddress = user.getEmail();
        try {
            HtmlEmail email = new HtmlEmail();

            email.setHostName("smtp.qq.com");
            email.setCharset("UTF-8");
            email.addTo(emailAddress);
            email.setFrom("2425724239@qq.com","LOVELY HOME 寻找您的爱巢");
            email.setAuthentication("2425724239@qq.com", "otyegwqttvwqeaeb");
            email.setSubject("爱巢 恭喜，出租房审核通过啦！");
            String context = "亲爱的用户"+ user.getName() +  "，您好！十分感谢您对爱巢的信赖！您发布的的出租房" + house.getName()+" (" +
                    house.getDescription() + ") 已经成功发布！";
            email.setMsg(context);
            email.send();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
