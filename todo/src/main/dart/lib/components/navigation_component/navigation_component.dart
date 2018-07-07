import 'package:angular/angular.dart';
import 'package:cleanTest2/src/notes_component/notes_component.dart';
//profile component
import 'package:cleanTest2/src/login_component/login_component.dart';
import 'package:cleanTest2/src/user_component/profile_component.dart';
import 'package:cleanTest2/src/adminpage_component/adminpage_component.dart';
import 'package:cleanTest2/src/user_component/User.dart';

//Navigationscomponente, die es erlaubt zwischen dem Profile und dem Notesview hin- und herzuschalten
@Component(
  selector: 'nav-comp',
  templateUrl: 'navigation_component.html',
  styleUrls: const ['navigation_component.css'],
  directives: const[CORE_DIRECTIVES, NotesComponent, LoginComponent, ProfileComponent, AdminpageComponent ],
)
class NavigationComponent{

  //Userobjekt des angemeldeten Users, erhalten von der Logincomponente
  @Input()
  User currentUser;

  Page currentPage = Page.NOTES;

  Page get PROFILE => Page.PROFILE;
  Page get NOTES => Page.NOTES;
  Page get ADMIN => Page.ADMIN;

  void switchToProfile() {
    currentPage = Page.PROFILE;
  }

  void switchToNotes() {
    currentPage = Page.NOTES;
  }

  void switchToAdminpage() {
    currentPage = Page.ADMIN;
  }
}

enum Page{
  PROFILE, NOTES, ADMIN
}