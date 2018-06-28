import 'package:angular/angular.dart';
import 'package:angular_components/angular_components.dart';

import 'src/notes_component/notes_component.dart';
import 'src/login_component/login_component.dart';
import 'src/navigation_component/navigation_component.dart';

// AngularDart info: https://webdev.dartlang.org/angular
// Components info: https://webdev.dartlang.org/components

@Component(
  selector: 'my-app',
  styleUrls: const ['app_component.css'],
  templateUrl: 'app_component.html',
  directives: const [materialDirectives, NotesComponent, LoginComponent, NavigationComponent],
  providers: const [materialProviders],
)
class AppComponent{}
