package com.ztyedu.mybatisplus;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import com.ztyedu.mybatisplus.common.CustomException;
import com.ztyedu.mybatisplus.mapper.EmployeeMapper;
import com.ztyedu.mybatisplus.mapper.GoodMapper;
import com.ztyedu.mybatisplus.mapper.GoodRecordsMapper;
import com.ztyedu.mybatisplus.mapper.UserMapper;
import com.ztyedu.mybatisplus.pojo.Employee;
import com.ztyedu.mybatisplus.pojo.Good;
import com.ztyedu.mybatisplus.pojo.GoodRecords;
import com.ztyedu.mybatisplus.pojo.User;
import com.ztyedu.mybatisplus.service.UserService;
import com.ztyedu.mybatisplus.service.impl.GoodRecordsServiceImpl;
import com.ztyedu.mybatisplus.service.impl.GoodServiceImpl;
import com.ztyedu.mybatisplus.service.impl.UserServiceImpl;
import com.ztyedu.mybatisplus.utils.SpringBootUtil;
import com.ztyedu.mybatisplus.utils.Utility;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.DigestUtils;

import javax.rmi.CORBA.Util;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@MapperScan("com.ztyedu.mybatisplus.mapper")
public class CanteenManageSystemApplication {
    private boolean loop = true;
    private String key = "";

    public static void main(String[] args) {
        SpringApplication.run(CanteenManageSystemApplication.class, args);
        new CanteenManageSystemApplication().showMainMenu();
    }

    public void showMainMenu() {

        while (loop) {
            System.out.println("====================小卖部管理系统(1.0)====================");
            System.out.println("\t\t 1.普通用户登录");
            System.out.println("\t\t 2.退出小卖部管理系统");
            System.out.println("\t\t 3.小卖部用户注册");
            System.out.println("\t\t 4.小卖部用户注销");
            System.out.println("\t\t 5.小卖部管理员登录");
            System.out.print("请输入你的选择: ");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("请输入您的用户账号: ");
                    Integer userId = Utility.readInt();
                    System.out.print("请输入您的密码: ");
                    String password = Utility.readString(32);
                    //进行MD5加密后再调用方法和数据库进行比对
                    password = DigestUtils.md5DigestAsHex(password.getBytes());
                    //获得容器，然后调用UserMapper
                    UserMapper userMapper = SpringBootUtil.getBean(UserMapper.class);
                    User user = userMapper.selectByUserIdAndUserPassword(userId, password);
                    if (user != null && user.getStatus() == 1 && user.getIsDeleted() == 0) {
                        System.out.println("====================用户(" + user.getUserName() + ")登录成功====================");
                        while (loop) {
                            System.out.println("====================小卖部管理系统(1.0)(二级菜单)(用户版)====================");
                            System.out.println("\t\t 1.显示货物");
                            System.out.println("\t\t 2.购买货物");
                            System.out.println("\t\t 3.查询账单记录");
                            System.out.println("\t\t 4.退出小卖部管理系统");
                            System.out.println("\t\t 5.查看余额");
                            System.out.println("\t\t 6.充值会员卡");
                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    GoodMapper goodMapper = SpringBootUtil.getBean(GoodMapper.class);
                                    List<Good> goods = goodMapper.selectAll();
                                    System.out.println("商品名称\t\t价格\t\t\t商品数量\t\t\t购买者账号\t\t\t物品状态\t\t\t创建时间\t\t\t\t\t\t更新时间\t\t\t\t\t\t是否下架");
                                    for (Good good : goods) {
                                        System.out.println(good);
                                    }
                                    break;
                                case "2":
                                    //购买货物需要调用GoodRecordsServiceImpl
                                    //因为购买货物需要减少Good表中的商品数量，然后再添加购物记录到GoodRecords表
                                    System.out.print("请输入你要购买的物品名称: ");
                                    String commodityName = Utility.readString(32);
                                    System.out.print("请输入你要购买的物品数量: ");
                                    Integer commodityNumber = Utility.readInt();
                                    GoodRecordsServiceImpl goodRecordsService = SpringBootUtil.getBean(GoodRecordsServiceImpl.class);
                                    boolean result = goodRecordsService.insertGoodRecords(commodityName, commodityNumber,user);
                                    if(result == true){
                                        System.out.println("物品已成功购买~");
                                    }else{
                                        System.out.println("物品购买失败");
                                    }
                                    break;
                                case "3":
                                    GoodRecordsMapper goodRecordsMapper = SpringBootUtil.getBean(GoodRecordsMapper.class);
                                    List<GoodRecords> goodRecords = goodRecordsMapper.selectByUserId(user.getUserId());
                                    System.out.println("商品名称\t\t价格\t\t\t购买数量\t\t\t购买者账号\t\t\t购买人\t\t\t创建时间\t\t\t\t\t\t更新时间");
                                    for (GoodRecords goodRecord : goodRecords) {
                                        System.out.println(goodRecord);
                                    }
                                    break;
                                case "4":
                                    loop = false;
                                    System.out.println("您退出了小卖部管理系统");
                                    break;
                                case "5":
                                    Integer checkBalanceUserId = user.getUserId();
                                    UserMapper checkBalanceMapper = SpringBootUtil.getBean(UserMapper.class);
                                    User checkUser = checkBalanceMapper.selectByUserId(checkBalanceUserId);
                                    System.out.println("账号为: " + checkBalanceUserId + " 的余额还剩: ￥" + checkUser.getBalance());
                                    break;
                                case "6":
                                    //调用UserMapper的充值方法
                                    System.out.print("请输入你要充值的账号；");
                                    int rechargeId = Utility.readInt();
                                    System.out.print("你是否要给账号: " + rechargeId+"充值(Y/N)");
                                    String makeSure = Utility.readString(1);
                                    if(makeSure.equals("Y")){
                                        System.out.print("请输入你要充值的金额(整数): ");
                                       BigDecimal rechargeBalance = new Scanner(System.in).nextBigDecimal();
                                        BigDecimal balance1 = user.getBalance();
                                        BigDecimal newBalance = balance1.add(rechargeBalance);
                                        int result2 = userMapper.updateBalanceByUserId(newBalance, rechargeId);
                                        if(result2 != -1){
                                            System.out.println("充值成功");
                                            UserMapper chargeMapper = SpringBootUtil.getBean(UserMapper.class);
                                            User user1 = chargeMapper.selectByUserId(rechargeId);
                                            BigDecimal afterRechargeBalance = user1.getBalance();
                                            System.out.println(rechargeId+" 账号余额还剩￥" + afterRechargeBalance);
                                        }else{
                                            throw new CustomException("由于未知错误 充值失败");
                                        }

                                    }
                                    break;
                                default:
                                    System.out.println("您的输入有误请重新输入");
                            }
                        }
                    } else {
                        throw new CustomException("用户名账号和密码不符合或账号被禁用或账号已注销");
                    }
                    break;
                case "2":
                    loop = false;
                    System.out.println("退出小卖部管理系统");
                    break;
                case "3":
                    System.out.println("请问您是否要进行用户注册(-1退出 1进入)");
                    String word = Utility.readString(2);
                    if (word.equals("1")) {
                        //调用注册用户的方法
                        System.out.print("请输入你要注册的用户账号: ");
                        Integer userNewId = Utility.readInt();
                        //调用UserMapper中的selectByUserId方法 查询是否已经有相同账号
                        UserMapper userMapper2 = SpringBootUtil.getBean(UserMapper.class);
                        if (userMapper2.selectByUserId(userNewId) == null) {
                            System.out.print("请输入你注册账号的密码: ");
                            String newPassWord = Utility.readString(11);
                            //新密码进行md5加密，保证存入数据库为加密后的密码
                            newPassWord = DigestUtils.md5DigestAsHex(newPassWord.getBytes());
                            System.out.print("请输入你要注册账号的昵称: ");
                            String newUserName = Utility.readString(32);
                            User newUser = new User();
                            newUser.setUserId(userNewId);
                            newUser.setUserName(newUserName);
                            newUser.setUserPassword(newPassWord);
                            UserService userService = SpringBootUtil.getBean(UserService.class);
                            boolean result = userService.insertAll(newUser);
                            if (result == true) {
                                System.out.println("====================用户(" + newUserName + ")注册成功====================");
                            } else {
                                System.out.println("====================用户注册失败====================");
                            }
                        } else {
                            //当账号存在重复时，抛出业务异常
                            throw new CustomException("您注册的用户账号已存在");
                        }
                    }
                    break;
                case "4":
                    //调用UserMapper中的用户注销
                    System.out.println("请问您是否要进行注销(-1退出 1进入)");
                    String keyWord = Utility.readString(2);
                    if (keyWord.equals("1")) {
                        System.out.print("请输入你要注销的用户账号: ");
                        Integer logoutUserId = Utility.readInt();
                        System.out.print("请输入你要注销的用户密码: ");
                        String logoutPWD = Utility.readString(32);
                        logoutPWD = DigestUtils.md5DigestAsHex(logoutPWD.getBytes());
                        UserMapper logoutUserMapper = SpringBootUtil.getBean(UserMapper.class);
                        int result = logoutUserMapper.deleteByUserIdAndUserPassword(logoutUserId, logoutPWD);
                        if (result != -1) {
                            System.out.println("====================用户注销成功====================");
                        } else {
                            System.out.println("====================用户注销失败====================");
                        }

                    }

                    break;
                case "5":
                    System.out.print("请输入您的用户账号: ");
                    int empId = Utility.readInt();
                    System.out.print("请输入您的密码: ");
                    String empPassword = Utility.readString(32);
                    //md5加密
                    empPassword = DigestUtils.md5DigestAsHex(empPassword.getBytes());
                    //调用EmployeeMapper中的方法 完成管理员登录校验
                    EmployeeMapper employeeMapper = SpringBootUtil.getBean(EmployeeMapper.class);
                    Employee employee = employeeMapper.selectByEmpIdAndEmpPassword(empId, empPassword);
                    if (employee != null && employee.getStatus() == 1 && employee.getIsDeleted() == 0) {
                        System.out.println("====================用户(" + employee.getEmpName() + ")登录成功====================");
                        while (loop) {
                            System.out.println("====================小卖部管理系统(1.0)(二级菜单)(管理员版)====================");
                            System.out.println("\t\t 1.显示货物");
                            System.out.println("\t\t 2.增加货物");
                            System.out.println("\t\t 3.删除货物");
                            System.out.println("\t\t 4.修改货物价格");
                            System.out.println("\t\t 5.查询交易记录");
                            System.out.println("\t\t 6.退出小卖部管理系统");
                            System.out.print("请输入你的选择: ");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    GoodMapper goodMapper = SpringBootUtil.getBean(GoodMapper.class);
                                    List<Good> goods = goodMapper.selectAll();
                                    System.out.println("商品名称\t\t价格\t\t\t商品数量\t\t\t购买者账号\t\t\t物品状态\t\t\t创建时间\t\t\t\t\t\t更新时间\t\t\t\t\t\t是否下架");
                                    for (Good good : goods) {
                                        System.out.println(good);
                                    }
                                    break;
                                case "2":
                                    System.out.println("请问您是否要进行添加货物(Y/N): ");
                                    String addKey = Utility.readString(1);
                                    if(addKey.equals("Y")) {
                                        GoodServiceImpl goodService = SpringBootUtil.getBean(GoodServiceImpl.class);
                                        Good good = new Good();
                                        System.out.print("请输入你要添加的商品名称: ");
                                        String goodName = Utility.readString(32);
                                        GoodMapper goodMapper1 = SpringBootUtil.getBean(GoodMapper.class);
                                        Good good1 = goodMapper1.selectByGoodName(goodName);
                                        if(good1 != null){
                                            System.out.print("请输入你要把当前商品数量增加到(0-100): ");
                                            Integer goodNumber = Utility.readInt();
                                            good.setGoodName(goodName);
                                            good.setGoodNumber(goodNumber);
                                            good.setStatus(1);
                                            good.setIsDeleted(0);
                                            boolean result3 = goodService.insertGood(good,employee.getEmpId());
                                            if(result3 == true){
                                                System.out.println("货物添加成功");
                                            }else{
                                                System.out.println("货物添加失败");
                                            }
                                        }else{
                                            System.out.print("请输入你要添加的商品价格: ");
                                            BigDecimal goodPrice = new Scanner(System.in).nextBigDecimal();
                                            System.out.print("请输入你要把当前商品数量增加到(0-100): ");
                                            Integer goodNumber = Utility.readInt();
                                            good.setGoodName(goodName);
                                            good.setGoodPrice(goodPrice);
                                            good.setGoodNumber(goodNumber);
                                            good.setStatus(1);
                                            good.setIsDeleted(0);
                                            boolean result3 = goodService.insertGood(good,employee.getEmpId());
                                            if(result3 == true){
                                                System.out.println("货物添加成功");
                                            }else{
                                                System.out.println("货物添加失败");
                                            }
                                        }

                                    }
                                    break;
                                case "3":
                                    System.out.println("请确定是否要进行删除货物(Y/N): ");
                                    String deleteKey = Utility.readString(1);
                                    if(deleteKey.equals("Y")){
                                        System.out.print("请输入你要删除的货物名称: ");
                                        String deleteGoodName = Utility.readString(32);
                                        GoodMapper deleteMapper = SpringBootUtil.getBean(GoodMapper.class);
                                        int result = deleteMapper.updateIsDeletedByGoodName(1, deleteGoodName);
                                        if(result != -1){
                                            System.out.println("删除货物成功");
                                        }else{
                                            System.out.println("删除货物失败");
                                        }
                                    }
                                    break;
                                case "4":
                                    System.out.println("请确定是否要修改商品价格(Y/N): ");
                                    String updateKey = Utility.readString(1);
                                    if(updateKey.equals("Y")){
                                        System.out.println("请输入你要修改的商品名称: ");
                                        String updateGoodName = Utility.readString(32);
                                        //查询是否有该商品，如果没有抛出异常
                                        GoodMapper updateMapper = SpringBootUtil.getBean(GoodMapper.class);
                                        if(updateMapper.selectByGoodName(updateGoodName)==null){
                                            throw new CustomException("您要修改的商品名称不存在");
                                        }
                                        System.out.println("请输入你要修改此商品的价格到多少: ");
                                        BigDecimal updatePrice =  new Scanner(System.in).nextBigDecimal();
                                        int result = updateMapper.updateGoodPriceByGoodName(updatePrice, updateGoodName);
                                        if( result != -1){
                                            System.out.println("商品价格修改成功");
                                        }else{
                                            System.out.println("商品价格修改失败");
                                        }
                                    }
                                    break;
                                case "5":
                                    GoodRecordsMapper goodRecordsMapper2 = SpringBootUtil.getBean(GoodRecordsMapper.class);
                                    List<GoodRecords> goodRecordsList = goodRecordsMapper2.selectAll();
                                    System.out.println("商品名称\t\t价格\t\t\t购买数量\t\t\t购买者账号\t\t\t购买人\t\t\t创建时间\t\t\t\t\t\t更新时间");
                                    for (GoodRecords goodRecord : goodRecordsList) {
                                        System.out.println(goodRecord);
                                    }
                                    break;
                                case "6":
                                    loop = false;
                                    System.out.println("您退出了小卖部管理系统");
                                    break;
                                default:
                                    System.out.println("您的输入有误请重新输入");
                            }
                        }
                    } else {
                        throw new CustomException("用户名账号和密码不符合或账号被禁用或账号已注销");
                    }
                    break;

            }

        }
    }

}
