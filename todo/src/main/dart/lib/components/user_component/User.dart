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
    'joined' : joined.toString(),
    'roles' : roles.toString(),
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

String dateTimeToDateString(DateTime dt){
  var formatter = new DateFormat('dd-MM-yyyy');
  String formatted = formatter.format(dt);
  return formatted;
}

