import 'package:angular/core.dart';
import 'package:http/http.dart';
import 'dart:async';
import 'dart:convert';

import 'package:todo/components/login_component/LoginBean.dart';
import 'package:todo/components/user_component/User.dart';

@Injectable()
class LoginService{
  final Client _http;

  LoginService(this._http);

  //POST
  Future<User> login(String username, String password) async{
    try{
      LoginBean lb = new LoginBean();
      lb.username = username;
      lb.password = password;

      final response = await _http.post('api/login', headers: {'Content-Type':'application/json'},
          body: JSON.encode(lb.toJson()));
      final user = new User.fromJason(_extractData(response));

      return user;
    }
    catch(e){

    }
  }
}

Exception _handleError(dynamic e){
  print(e);
  return new Exception('Server error; cause $e');
}

//Dekodierung der JSON response
dynamic _extractData(Response resp) => JSON.decode(resp.body);

bool jsonToString(String jsonString){
  jsonString = jsonString.toString();
  jsonString = jsonString.replaceAll("[", "");
  jsonString = jsonString.replaceAll("]", "");
  jsonString = jsonString.replaceAll("{", "");
  jsonString = jsonString.replaceAll("}", "");
  jsonString = jsonString.trim();

  if(jsonString == "true"){
    return true;
  }
  else{
    return false;
  }
}