class LoginBean{

  Map toJson() => {
    'username' : username,
    'password' : password,
  };

  String username;
  String password;


}