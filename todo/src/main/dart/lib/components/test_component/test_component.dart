import 'package:angular/angular.dart';
import 'package:angular_components/angular_components.dart';
import 'package:cleanTest2/src/note_form_component/note_form_component.dart';
import 'package:angular_forms/angular_forms.dart';

import 'dart:js_util';

import 'notes_service.dart';
import 'Note.dart';
import 'dart:async';

@Component(
  selector: 'test-comp',
  templateUrl:'test_component.html',
  directives:  const [CORE_DIRECTIVES, NotesFormComponent, formDirectives],
    providers: const [NotesService]
)
class TestComponent implements OnInit{

  final NotesService notesService;

  List<Note> noteList =[];
  Note selectedNote;
  bool showForm = false;
  bool editing = false;

  TestComponent(this.notesService);

  void selectNote(Note note){
    showForm = false;
    selectedNote = note;
    editing = false;
  }

  void deleteNote(Note note){
    selectedNote = null;
    //noteList.remove(note);
    notesService.deleteNote(note);
    noteList = notesService.getNoteList();
  }

  void createAddForm(){
    print("addForm");
    selectedNote = null;
    showForm = true;
    editing = false;
  }

  void updateNote(DateTime dueDate, String headline, Relevance relevance){

    selectedNote.author = "thisProfile";
    selectedNote.created = new DateTime.now();
    selectedNote.due = dueDate;
    selectedNote.headline = headline;
    selectedNote.lastEdited = new DateTime.now();
    selectedNote.relevance = relevance;

    notesService.updateNote(selectedNote);

    selectedNote = null;

    noteList = notesService.getNoteList();

    cancelUpdate();
  }

  void cancelUpdate(){
    editing = false;


  }


  @override
  void ngOnInit(){
    print("init");
    noteList = notesService.getNoteList();
    print(noteList.length);
    //Note newNote = new Note();
    //newNote.author = "post-hoc added note";
    //noteList.add(newNote);
  }

  void submitAddForm(DateTime dueDate, String headline, Relevance relevance){
    Note newNote = new Note();
    newNote.author = "thisProfile";
    newNote.created = new DateTime.now();
    newNote.due = dueDate;
    newNote.headline = headline;
    newNote.lastEdited = new DateTime.now();
    newNote.relevance = relevance;

    notesService.addNote(newNote);
    noteList = notesService.getNoteList();
  }

  void startEditNote(){
    editing = true;

  }

}