import 'package:angular/angular.dart';

import 'package:cleanTest2/app_component.dart';
import 'package:http/browser_client.dart';
import 'package:http/http.dart';

void main() {
  bootstrap(AppComponent, [provide(Client, useFactory: () => new BrowserClient(), deps: [])]);
}
