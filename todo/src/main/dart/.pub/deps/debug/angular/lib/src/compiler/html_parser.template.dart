// GENERATED CODE - DO NOT MODIFY BY HAND

// **************************************************************************
// Generator: TemplateGenerator
// **************************************************************************

// ignore_for_file: cancel_subscriptions,constant_identifier_names,duplicate_import,non_constant_identifier_names,library_prefixes,UNUSED_IMPORT,UNUSED_SHOWN_NAME
import 'html_parser.dart';
export 'html_parser.dart';
import 'package:source_span/source_span.dart';
import 'html_ast.dart';
import 'html_lexer.dart' show HtmlToken, HtmlTokenType, tokenizeHtml;
import 'html_tags.dart' show getHtmlTagDefinition, getNsPrefix, mergeNsAndName;
import 'parse_util.dart' show ParseError;
// Required for initReflector().
import 'html_ast.template.dart' as _ref0;
import 'html_lexer.template.dart' as _ref1;
import 'html_tags.template.dart' as _ref2;
import 'parse_util.template.dart' as _ref3;

var _visited = false;
void initReflector() {
  if (_visited) {
    return;
  }
  _visited = true;
  _ref0.initReflector();
  _ref1.initReflector();
  _ref2.initReflector();
  _ref3.initReflector();
}
