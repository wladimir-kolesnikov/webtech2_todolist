import 'package:angular/angular.dart';

import 'package:cleanTest2/src/user_component/user_service.dart';
import 'package:cleanTest2/src/user_component/User.dart';
import 'package:cleanTest2/src/notes_component/notes_component.dart';

import 'dart:async';
import 'package:intl/intl.dart';

@Component(
    selector: 'admin-comp',
    templateUrl:'adminpage_component.html',
    styleUrls: const ['adminpage_component.css'],
    directives:  const [CORE_DIRECTIVES, NotesComponent],
    providers: const [UserService]
)
class AdminpageComponent implements OnInit{

  final UserService userService;

  AdminpageComponent(this.userService);

  User selectedUser;
  bool users = true;
  bool notes = false;

  List<User> userList =[];

  @override
  Future ngOnInit() async{
    userList = await userService.getUserList();
  }

  void selectUser(User user){
    users = false;
    selectedUser = user;
    //selectedNote.created = parseDateTimetoDate(note.created.toIso8601String());
    //selectedNote.lastEdited = parseDateTimetoDate(note.lastEdited.toIso8601String());
    //selectedNote.due = parseDateTimetoDate(note.due.toIso8601String());
    notes = true;
  }

  String dateTimeToDateString(DateTime dt){
    var formatter = new DateFormat('dd-MM-yyyy');
    String formatted = formatter.format(dt);
    return formatted;
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

  void goBack(){
    users = true;
    notes = false;
  }

}