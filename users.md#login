#login
## 1.用户
<a name="login" />
### 登录
**`GET` `/mobileapi/customer/login`**
用户登录接口，登录后返回用户`cookies`，`cookies`名称`frontend`,所有调用关于用户的接口都必须带上这个`cookies`.
**_Paramers_**
* `username` - 用户登录账户
* `password` - 用户登录密码
**_Examples_**
```
    /mobileapi/customer/login?username=zliu@kalengo.com&password=kalengo2013
```
**_Response_**
```js
    {
        code: 0, //0是成功，非0都是失败
        msg: null, //返回信息
        model: {
            name: "leo liu",  //用户名
            email: "zliu@kalengo.com", //邮箱
            avatar: null,  //头像图片url
            tel: null,  //联系电话
            session: "6urtol84os63pp67k926ncqdh1" //前端cookies frontend的值
        }
    }
```
---------------------------------------
