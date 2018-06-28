import 'package:angular/angular.dart';
import 'package:http/testing.dart';
import 'package:http/http.dart';

import 'package:cleanTest2/src/notes_component/Note.dart';
import 'package:cleanTest2/src/user_component/User.dart';

import 'dart:async';
import 'dart:convert';

@Injectable()
class InMemoryDatabaseService extends MockClient {

  static List<List<Note>> mockNotesList = [];
  static List<User> mockUserList = [];
  static int nextNotesID = 4;
  static int nextUserID = 2;

  InMemoryDatabaseService() : super(_handler){
    mockNotesList.insert(0, getNotes());
    List<Note> newList = [];
    mockNotesList.insert(1, newList );
    mockUserList = getUsers();
  }

  static Future<Response> _handler(Request request) async {
    var data;

    switch(request.method){
      case 'GET':
        final id = int.parse(request.url.pathSegments.last, onError: (source) => null);
        if(id != null){
          if(request.url.pathSegments.contains('notes')){
            data = mockNotesList[id].firstWhere((note) => note.id == id);
          }
          else if(request.url.pathSegments.contains('users')){
            data = mockUserList.firstWhere((user) => user.id == id);
          }
        }
        else if(request.url.pathSegments.contains('notes')){
          print(mockNotesList.length);
          data = mockNotesList[int.parse(request.url.pathSegments[2])];
        }
        else if(request.url.pathSegments.contains('users')){
          data = mockUserList;
        }
        break;
      case 'POST' :
        if(request.url.pathSegments.contains('notes')) {
          var author = JSON.decode(request.body)['author'];
          var headline = JSON.decode(request.body) ['headline'];
          var created = JSON.decode(request.body) ['created'];
          var lastEdited = JSON.decode(request.body) ['lastEdited'];
          var due = JSON.decode(request.body) ['due'];
          var relevance = JSON.decode(request.body) ['relevance'];
          var content = JSON.decode(request.body) ['content'];
          var id = nextNotesID++;

          Note newNote = new Note();
          newNote.author = author;
          newNote.headline = headline;
          newNote.created = created;
          newNote.lastEdited = lastEdited;
          newNote.due = due;
          newNote.relevance = relevance;
          newNote.id = id;
          newNote.content = content;

          print(mockNotesList.length);
          mockNotesList[int.parse(request.url.pathSegments[2])].add(newNote);
          print(mockNotesList.length);
          data = newNote;
        }

        else if(request.url.pathSegments.contains('users')){
          var username = JSON.decode(request.body)['username'];
          var password = JSON.decode(request.body)['password'];
          var joined = JSON.decode(request.body)['joined'];
          var roles = JSON.decode(request.body)['roles'];
          var id = nextUserID++;

          User newUser = new User();
          newUser.username = username;
          newUser.password = password;
          newUser.joined = joined;
          newUser.roles = roles;
          newUser.id = id;

          mockUserList.add(newUser);
          mockNotesList.insert(id, new List<Note>());
          data = newUser;
        }
        break;

      case 'PUT' :
        if(request.url.pathSegments.contains('notes')) {
          Note noteChanges = new Note.fromJson(JSON.decode(request.body));
          Note targetNote = mockNotesList[int.parse(request.url.pathSegments[2])].firstWhere((note) =>
          note.id == noteChanges.id);

          targetNote.headline = noteChanges.headline;
          targetNote.relevance = noteChanges.relevance;
          targetNote.lastEdited = new DateTime.now();
          targetNote.due = noteChanges.due;
          targetNote.content = noteChanges.content;

          data = targetNote;
        }
        else if(request.url.pathSegments.contains('users')){
          User userChanges = new User.fromJason(JSON.decode(request.body));
          User targetUser = mockUserList.firstWhere((user) => user.id == userChanges.id);

          targetUser.password = userChanges.password;

          data = targetUser;
        }
        break;

      case 'DELETE' :
        int id = int.parse(request.url.pathSegments.last);
        mockNotesList[int.parse(request.url.pathSegments[2])].removeWhere((note) => note.id == id);
        break;

      default:
        throw 'Unimplementd HTTP method ${request.method}';
    }

    Response response = new Response(JSON.encode({'data' : data }), 200,
        headers: {'content-type' : 'application/json'});

    return response;
  }

}

List<Note> getNotes() {
  List<Note> newList = [];

  Note note1 = new Note();
  note1.id =  1;
  note1.author = "User 1";
  note1.created = new DateTime.now();
  note1.due = new DateTime.now();
  note1.headline = "first note";
  note1.lastEdited = new DateTime.now();
  note1.relevance = Relevance.NORMAL;
  //idCount++;

  Note note2 = new Note();
  note2.id =  2;
  note2.author = "User 2";
  note2.created = new DateTime.now();
  note2.due = new DateTime.now();
  note2.headline = "second note";
  note2.lastEdited = new DateTime.now();
  note2.relevance = Relevance.IMPORTANT;

  Note note3 = new Note();
  note3.id =  3;
  note3.author = "User 3";
  note3.created = new DateTime.now();
  note3.due = new DateTime.now();
  note3.headline = "third note";
  note3.lastEdited = new DateTime.now();
  note3.relevance = Relevance.URGENT;

  newList.add(note1);
  newList.add(note2);
  newList.add(note3);



  return newList;
}

List<User> getUsers(){

  List<User> newList = [];

  User user1 = new User();
  user1.id = 0;
  user1.username = "user1";
  user1.password = "password1";
  user1.joined = new DateTime.now().toUtc();
  user1.roles = new Set<Role>();
  user1.roles.add(Role.USER);
  user1.roles.add(Role.SUPERMOD);


  User user2 = new User();
  user2.id = 1;
  user2.username = "user2";
  user2.password = "password2";
  user2.joined = new DateTime.now().toUtc();
  user2.roles = new Set<Role>();
  user2.roles.add(Role.ADMIN);

  newList.add(user1);
  newList.add(user2);

  return newList;

}