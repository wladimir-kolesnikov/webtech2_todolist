import 'package:angular/core.dart';
import 'User.dart';
import 'dart:async';
import 'dart:convert';
import 'package:http/http.dart';

import 'package:cleanTest2/src/in_memory_db_compnent/in_memory_db_component.dart';
import 'package:cleanTest2/src/user_component/User.dart';

//Userservice der Interaktionsfunktionen von User bezogenen Aufgaben mit dem Server übernimmt
@Injectable()
class UserService{
  final InMemoryDatabaseService inMemoryDatabaseService;

  UserService(this.inMemoryDatabaseService);

  //GET
  //Funtion holt alle User aus der Datenbank
  Future<List<User>> getUserList() async{
    try{
      final response = await inMemoryDatabaseService.get('api/users');
      print(_extractData(response).toString());
      final users = (_extractData(response) as List).map((json) => new User.fromJason(json)).toList();
      return users;
    }
    catch(e){
      _handleError(e);
    }
  }


  //POST
  //Fügt einen neuen User hinzu
  Future addUser(User user) async {
    try{
      final response = await inMemoryDatabaseService.post('api/users', headers: {'Conent-Type':'applicaton/json'},
          body: JSON.encode(user.toJson()));
    }
    catch(e){
      _handleError(e);
    }
  }


  //PUT
  //Updated die Daten eines bestimmten Users
  Future updateUser(User user) async {
    int id = user.id;
    try{
      final response = await inMemoryDatabaseService.put('api/users/$id', headers: {'Content-Type':'application/json'},
          body:JSON.encode(user.toJson()));
    }
    catch(e){
      _handleError(e);
    }
  }


  //umschreiben um mit Server zu interagieren
  //Die Loginfunktion überprüft ob es einen Userobjekt mit username gibt und überprüft ob das eingegebene
  //Passwort mit dem des Userobjekts übereinstimmt
  Future<User> loginUser(String username, String password) async {
    List<User> userList = await getUserList();

    for(int i = 0; i < userList.length; i++){
      if(userList[i].username == username){
        if(userList[i].password == password){
          return userList[i];
        }
        else{
          return null;
        }
      }
    }
    return null;
  }

  //Funtion holt einen User aus der Datenbank, der bestimmten Usernamen hat, sollte so einer existieren
  Future<User> findUserWithUsername(String username)async {
    List<User> userList = await getUserList();
    for(int i = 0; i < userList.length; i++) {
      if (userList[i].username == username) {
        return userList[i];
      }
    }
        return null;
  }
}

//Error handling
Exception _handleError(dynamic e){
  print(e);
  return new Exception('Server error; cause $e');
}

//Dekodierung der JSON response
dynamic _extractData(Response resp) => JSON.decode(resp.body)['data'];