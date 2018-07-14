import 'dart:async';
import 'package:angular/angular.dart';
import 'dart:convert';

import 'package:todo/components/in_memory_db_compnent/in_memory_db_component.dart';
import 'package:todo/components/user_component/user_service.dart';
import 'package:todo/components/user_component/User.dart';
import 'package:intl/intl.dart';

//Profilcomponente zum Anzeigen der Userdaten und Ändern des Passwortes
@Component(
  selector: 'profile-comp',
  templateUrl: 'profile_component.html',
  styleUrls: const ['profile_component.css'],
  directives: const [CORE_DIRECTIVES,],
  providers: const [UserService, InMemoryDatabaseService ]
)
class ProfileComponent implements OnInit{

  final UserService userService;
  ProfileComponent(this.userService);

  List<User> userList;
  bool editing = false;

  //Userobjekt des angemeldeten Users
  @Input()
  User currentUser;


  @override
  Future ngOnInit() async {
    userList = await userService.getUserList();
    print(currentUser.roles);
  }

  //Funktion zum updaten der User daten ( zZ nur Passwort )
  Future updateUser(String password) async {
      currentUser.password = password;

      await userService.updateUser(currentUser, password);
      userList = await userService.getUserList();
      cancelEditing();

  }

  //Editierung wird gestartet
  void startEditing(){
    editing = true;
  }

  //Editierung wird abgebrochen
  void cancelEditing(){
    editing = false;
  }

  String roleToString(Role rel){
    switch(rel){
      case Role.USER:
        return "User";
        break;
      case Role.ADMIN:
        return "Admin";
        break;
      case Role.SUPERMOD:
        return "Supermod";
        break;
    }
  }

  String dateTimeToDateString(DateTime dt){
    var formatter = new DateFormat('dd-MM-yyyy');
    String formatted = formatter.format(dt);
    return formatted;
  }

}