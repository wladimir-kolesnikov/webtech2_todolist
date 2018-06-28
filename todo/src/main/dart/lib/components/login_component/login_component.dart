import 'package:angular/angular.dart';
import 'package:angular_forms/angular_forms.dart';
import 'package:cleanTest2/src/notes_component/notes_component.dart';
import 'package:cleanTest2/src/user_component/user_service.dart';
import 'package:cleanTest2/src/user_component/User.dart';

import 'dart:async';

import 'package:cleanTest2/src/navigation_component/navigation_component.dart';
import 'package:cleanTest2/src/in_memory_db_compnent/in_memory_db_component.dart';


//Component implementiert den Login
@Component(
  selector: 'login-comp',
  templateUrl: 'login_component.html',
  styleUrls: const ['login_component.css'],
  directives: const [CORE_DIRECTIVES,formDirectives,NotesComponent, NavigationComponent],
  providers: const [UserService, InMemoryDatabaseService]
)
class LoginComponent implements OnInit {

  final UserService userService;

  //currentUser hält das Userobjekt nach dem anmelden und wird Angular in HTML weitergereicht
  User currentUser = null;

  List<User> userList;

  bool loginAccepted = false;
  bool registering = false;

  LoginComponent(this.userService);


  @override
  Future ngOnInit() async{
    userList = await userService.getUserList();
  }


  //umschreiben um mit Server zu interagieren
  //Die Loginfunktion überprüft ob es einen Userobjekt mit username gibt und überprüft ob das eingegebene
  //Passwort mit dem des Userobjekts übereinstimmt
  Future login(String username, String password) async {

    currentUser = await userService.loginUser(username, password);
    print(currentUser);

    if( currentUser != null) {
      loginAccepted = true;
    }
  }

  void startRegistration(){

    registering = true;

  }

  //Die Registerfunktion erstellt ein neues Userobjekt und sendet dieses an das Backend damit die DB
  //angepasst werden kann
  Future register(String userName, String password) async {
    User newUser = new User();
    newUser.username = userName;
    newUser.password = password;
    newUser.joined = new DateTime.now();
    newUser.roles = new Set<Role>();
    newUser.roles.add(Role.USER);

    await userService.addUser(newUser);

    userList = await userService.getUserList();
    cancelRegistration();

  }

  void cancelRegistration(){

    registering = false;

  }

}