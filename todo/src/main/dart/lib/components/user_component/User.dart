import 'dart:convert';
import 'package:intl/intl.dart';
import 'package:todo/components/user_component/RoleClass.dart';

//User Klasse als Datenstruktur für User Entität und Helferfunktionen für JSON
class User{

  int id;
  String username;
  //String password;
  DateTime joined;
  Set<Role> roles;

  User(){}

  //Userobjekt wird zu einer JSON kompatiblen Map umgewandelt
  Map toJson(String password) => {
    'id' : id,
    'username' : username,
    'joined' : dateTimeToDateString(joined),
    'roles' : makeSetOfRoleClass(roles),
    'password' : password
  };

  //Userobjekt wird aus einer JSON Map erstellt
  factory User.fromJason(Map<String, dynamic> user){

    User newUser = new User();
    newUser.id = user['id'];
    newUser.username = user['username'];
    //newUser.password = user['password'];
    //newUser.joined = DateTime.parse(user['joined']);
    newUser.joined = parseDateTimetoDate(user['joined']);
    newUser.roles = stringToRoleSet(user['roles']);

    return newUser;
  }
}

enum Role{
  ADMIN, SUPERMOD, USER
}

//Hilfsfunktion um String in einem Enum umzuwandeln
Role stringToEnum(String enumString){
  Role rEnum;
  enumString = enumString.trim();
  print(enumString);
  switch(enumString){
    case 'admin':
      rEnum = Role.ADMIN;
      break;
    case 'supermod':
      rEnum = Role.SUPERMOD;
      break;
    case 'user' :
      rEnum = Role.USER;
      break;
    default:
      rEnum = null;
      break;
  }
  print(rEnum.toString());
  return rEnum;
}

String rolesToString(Set<Role> roles){
  String finalString = "";
  for(int i = 0; i < roles.length; i++){
    Role role = roles.elementAt(i);
    finalString = finalString + roleToString(role);
  }
  return finalString;
}

String roleToString(Role role){
  String roleString;
  print(role);
  switch(role){
    case Role.ADMIN:
      roleString = 'admin';
      break;
    case Role.SUPERMOD:
      roleString = 'supermod';
      break;
    case Role.USER :
      roleString = 'user';
      break;
    default:
      roleString = null;
      break;
  }
  print(roleString);
  return roleString;
}

Set<Role> stringToRoleSet(List<String> stringList) {
  Set<Role> roleSet = new Set<Role>();
  if(stringList.isEmpty){
    return roleSet;
  } else {
    String setString = stringList.toString();
    print(setString);
    setString = setString.toString();
    setString = setString.replaceAll("[", "");
    setString = setString.replaceAll("]", "");
    setString = setString.replaceAll("{", "");
    setString = setString.replaceAll("}", "");
    List<String> splitString = setString.split(',');
    print(splitString);

    String roleNameSplitString = splitString[1];
    List<String> splitStringName = roleNameSplitString.split(':');

    print(splitStringName[1]);
    /*
  for(int i = 0; i < splitString.length; i++){
    roleSet.add(stringToEnum(splitString[i]));
  }
  */

    roleSet.add(stringToEnum(splitStringName[1]));

    return roleSet;
  }
}

String dateTimeToDateString(DateTime dt){
  var formatter = new DateFormat('yyyy-MM-dd');
  String formatted = formatter.format(dt);
  return formatted;
}

List<Map> makeSetOfRoleClass(Set<Role> rolesEnum){
  //String finalString = "";
  List<Map> finalRoleSet = new List<Map>();
  for(int i = 0; i < rolesEnum.length; i++){
    Role role = rolesEnum.elementAt(i);
    RoleClass roleClass = new RoleClass();
    roleClass.roleName = roleToString(role);
    roleClass.id = 1;
    Map roleMap = roleClass.toJson();
    print(finalRoleSet);
    print(roleClass);
    finalRoleSet.add(roleMap);
    print("check");
    print(finalRoleSet);

    var test = JSON.encode(finalRoleSet);
    print(test);
  }
  return finalRoleSet;
}


DateTime parseDateTimetoDate(List<String> dtS){
  //dtS = dtS.toString();
  print(dtS);
  //String dateString = dtS[0].toString() + "-" + dtS[1].toString() + "-" + dtS[2].toString();
  if(int.parse(dtS[1].toString()) < 10){
    dtS[1] = "0"+dtS[1].toString();
  }
  String dateString = dtS[0].toString() + dtS[1].toString() + dtS[2].toString();
  print(dateString);

  return DateTime.parse(dateString);
}

int _toInt(id) => id is int ? id : int.parse(id);

