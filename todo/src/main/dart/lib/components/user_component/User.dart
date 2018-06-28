//User Klasse als Datenstruktur für User Entität und Helferfunktionen für JSON
class User{

  int id;
  String username;
  String password;
  DateTime joined;
  Set<Role> roles;

  User(){}

  //Userobjekt wird zu einer JSON kompatiblen Map umgewandelt
  Map toJson() => {
    'id' : id,
    'username' : username,
    'password' : password,
    'joined' : joined.toString(),
    'roles' : roles.toString(),
  };

  //Userobjekt wird aus einer JSON Map erstellt
  factory User.fromJason(Map<String, dynamic> user){

    User newUser = new User();
    newUser.id = _toInt(user['id']);
    newUser.username = user['username'];
    newUser.password = user['password'];
    newUser.joined = DateTime.parse(user['joined']);
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
    case 'Role.ADMIN':
      rEnum = Role.ADMIN;
      break;
    case 'Role.SUPERMOD':
      rEnum = Role.SUPERMOD;
      break;
    case 'Role.USER' :
      rEnum = Role.USER;
      break;
    default:
      rEnum = null;
      break;
  }
  print(rEnum.toString());
  return rEnum;
}

//Hilfsfunktion um aus einem String eine Set zu erstellen
Set<Role> stringToRoleSet(String setString){
  setString = setString.replaceAll("{","");
  setString = setString.replaceAll("}","");
  List<String> splitString = setString.split(',');
  print(splitString);
  Set<Role> roleSet = new Set<Role>();
  for(int i = 0; i < splitString.length; i++){
    roleSet.add(stringToEnum(splitString[i]));
  }
  return roleSet;
}

int _toInt(id) => id is int ? id : int.parse(id);

