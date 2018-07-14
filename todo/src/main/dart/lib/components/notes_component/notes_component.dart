import 'package:angular/angular.dart';

import 'package:angular_forms/angular_forms.dart';
import 'package:todo/components/in_memory_db_compnent/in_memory_db_component.dart';
import 'package:todo/components/user_component/user_service.dart';
import 'package:todo/components/user_component/User.dart';

import 'notes_service.dart';
import 'Note.dart';
import 'dart:async';
import 'package:intl/intl.dart';

//Notescomponent, die die Liste an Notes eines Users anzeigt, Details einer einzelnen Note präsentiert und
//Möglichkeiten zum Hinzufügen, Löschen und Editieren bietet
@Component(
  selector: 'test-comp',
  templateUrl:'notes_component.html',
  styleUrls: const ['notes_component.css'],
  directives:  const [CORE_DIRECTIVES, formDirectives, ],
    providers: const [NotesService, UserService]
)
class NotesComponent implements OnInit{

  final NotesService notesService;
  final UserService userService;

  List<Note> noteList =[];
  Note selectedNote;
  bool showForm = false;
  bool editing = false;
  bool check = false;

  String errorMessage = "";
  String formsTitle ="";

  //User ID des angemeldeten Users
  @Input()
  int userID;

  NotesComponent(this.notesService, this.userService);

  @override
  Future ngOnInit() async{
    noteList = await notesService.getNoteList(userID);
  }

  // Nach dem drücken auf eine der Notes, werden die Details diese Note angezeigt
  void selectNote(Note note){
    showForm = false;
    selectedNote = note;
    //selectedNote.created = parseDateTimetoDate(note.created.toIso8601String());
    //selectedNote.lastEdited = parseDateTimetoDate(note.lastEdited.toIso8601String());
    //selectedNote.due = parseDateTimetoDate(note.due.toIso8601String());
    editing = false;
    formsTitle = "Note Details";
  }

  //Löschen einer Note und refresh der Notes Liste
  Future deleteNote(Note note) async {
    selectedNote = null;
    notesService.deleteNote(note, userID);
    noteList = await notesService.getNoteList(userID);
  }

  //Nach dem Drücken des Hinzufügen Buttons wird die Hinzufügen Form angezeigt
  void createAddForm(){
    selectedNote = null;
    showForm = true;
    editing = false;
    errorMessage = "";
    formsTitle = "Add Note";
  }

  //Nach dem Drücken auf den Submitbutton wird die entsprechende Note mit den übergebenen Informationen geupdated
  Future updateNote(String dueDateStr, String headline, String relevanceStr, String content ) async{
    
    DateTime dueDate = parseDateTimetoDate(dueDateStr);
    Relevance relevance = enumFromString(relevanceStr);
    
    selectedNote.author = selectedNote.author;
    selectedNote.created = new DateTime.now();
    DateTime newDueDate = DateTime.parse(dueDate.toString());
    selectedNote.due = newDueDate;
    selectedNote.headline = headline;
    selectedNote.lastEdited = new DateTime.now();
    selectedNote.relevance = relevance;
    selectedNote.content = content;

    Note returnedNote = await notesService.updateNote(selectedNote, userID);
    if(returnedNote == null){
      errorMessage ="Error when editing";
    }
    else {
      errorMessage ="";
      formsTitle = "Note Details";
      cancelUpdate();
    }
    //selectedNote = null;
    noteList = await notesService.getNoteList(userID);

  }

  //Die Upateform wird wieder versteckt
  void cancelUpdate(){
    editing = false;
    formsTitle = "Note Details";
  }


  //Nach dem Drücken des Submitbuttons wird eine neue Note mit den entsprechenden Daten erzeugt
 Future submitAddForm(String dueDateStr, String headline, String relevanceStr, String content) async{
   
    DateTime dueDate = parseDateTimetoDate(dueDateStr);
    Relevance relevance = enumFromString(relevanceStr);
   
    Note newNote = new Note();
    newNote.author = "thisProfile";
    newNote.created = new DateTime.now();
    //DateTime newDueDate = DateTime.parse(dueDate.toString());
    newNote.due = dueDate;
    newNote.headline = headline;
    newNote.lastEdited = new DateTime.now();
    newNote.relevance = relevance;
    newNote.content = content;

    Note returnedNote = await notesService.addNote(newNote, userID);
    if(returnedNote == null){
      errorMessage ="Error when adding";
    }
    else {
      errorMessage ="";
    }
    print(returnedNote);
    noteList = await notesService.getNoteList(userID);
  }

  void startEditNote(){
    editing = true;
    errorMessage = "";
    formsTitle = "Edit Note";
  }

  //Nach dem Drücken des entsprechenden Buttons wird seletktiere Note dem User übergeben der in dem Eingabefeld
  //eingetragen wurde, wenn so ein User mit dem  Username existiert
  Future sendToUser(String username) async {
    User user = await userService.findUserWithUsername(username);
    int newUserID = user.id;

    await notesService.addNote(selectedNote, newUserID);

    notesService.deleteNote(selectedNote, userID);
    selectedNote = null;
    noteList =  await notesService.getNoteList(userID);
  }

  DateTime parseDateTimetoDate(String dtS){
    DateTime dt = DateTime.parse(dtS);
    var formatter = new DateFormat('yyyy-MM-dd');
    String formatted = formatter.format(dt);
    print(formatted);
    return DateTime.parse(formatted);
  }

  String dateTimeToDateString(DateTime dt){
    var formatter = new DateFormat('dd-MM-yyyy');
    String formatted = formatter.format(dt);
    return formatted;
  }

  String relevanceToString(Relevance rel){
    switch(rel){
      case Relevance.NORMAL:
        return "Normal";
        break;
      case Relevance.IMPORTANT:
        return "Important";
        break;
      case Relevance.URGENT:
        return "Urgent";
        break;
    }
  }
  
  Relevance enumFromString(String enumString){
    print(enumString);
    Relevance rEnum;
    switch(enumString){
      case 'Relevance.NORMAL' :
        rEnum = Relevance.NORMAL;
        break;
      case 'Relevance.IMPORTANT' :
        rEnum = Relevance.IMPORTANT;
        break;
      case 'Relevance.URGENT' :
        rEnum = Relevance.URGENT;
        break;
      default:
        rEnum = null;
        break;
    }
    rEnum.toString();
    return rEnum;
  }

}
