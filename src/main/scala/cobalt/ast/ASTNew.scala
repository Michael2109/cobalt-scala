package cobalt.ast

import cobalt.ast.Ast.stmt.Import

object ASTNew {

  case class Module(header: ModuleHeader, models: Seq[Model])

  case class ModuleHeader(nameSpace: NameSpace, imports: Seq[Import])

  case class Import(loc: Seq[String])

  case class Model(name: Name, `type`: ModelType, modifiers: Seq[Modifier], fields: Seq[Field], parent: Option[Type], parentArguments: Seq[Expression], interfaces: Seq[Type], body: Statement)

  trait ModelType
  case class ClassModel() extends ModelType
  case class ObjectModel() extends ModelType
  case class TraitModel() extends ModelType

  case class Field(name: Name, `type`: Option[Type], init: Option[Type])

  trait Type
  case class Init() extends Type
  case class TypeRef(ref: Ref) extends Type
  case class TypeApp(ref: Ref, types: Seq[Type]) extends Type // Type application, aka Map<A,B> -> `TyApp (RefLocal "Map") [TyRef (RefLocal "A"), TyRef (RefLocal "B")]`
  //case class TypeRel(typeRel: TypeRel, `type1`: Type, `type2`: Type) extends Type // This allows things like <T extends Something> which would be `TyRel Extends (TyRef (RefLocal "T")) (TyRef (RefLocal "Something"))`

  trait Ref
  case class RefSpecial(specialRef: SpecialRef) extends Ref
  case class RefLocal(name: Name) extends Ref
  case class RefQual(qualName: QualName) extends Ref

  trait SpecialRef
  case class Super() extends SpecialRef
  case class This() extends SpecialRef

  trait TypeRel
  case class Inherits() extends TypeRel
  case class Extends() extends TypeRel
  case class Equals() extends TypeRel

  case class NameSpace(nameSpace: Seq[String])

  case class Name(name: String)
  case class QualName(nameSpace: NameSpace, name: Name)

  case class Annotation(name: Name)

  case class Method(name: Name, annotations: Seq[Annotation], fields: Seq[Field], modifiers: Seq[Modifier], returnType: Option[Type], body: Block)

  trait Modifier
  case class Public() extends Modifier
  case class Protected() extends Modifier
  case class Private() extends Modifier
  case class PackageLocal() extends Modifier
  case class Abstract() extends Modifier
  case class Final() extends Modifier
  case class Pure() extends Modifier

  trait Block
  case class Inline(expression: Expression)
  case class DoBlock(expression: Statement)

  // TODO Update args
  trait Expression
  case class BlockExpr(expressions: Seq[Expression]) extends Expression
  case class Identifier(name: Name) extends Expression
  case class MethodCall() extends Expression
  case class NewClassInstance() extends Expression
  case class StringLiteral(value: String) extends Expression
  case class Ternary() extends Expression
  case class Tuple() extends Expression
  case class BoolConst() extends Expression
  case class Not() extends Expression
  case class ABinary(op: ABinOp, expression1: Expression, expression2: Expression) extends Expression
  case class BBinary(op: BBinOp, expression1: Expression, expression2: Expression) extends Expression
  case class RBinary(op: RBinOp, expression1: Expression, expression2: Expression) extends Expression
  case class IntConst(value: Int) extends Expression
  case class DoubleConst(value: Double) extends Expression
  case class FloatConst(value: Float) extends Expression
  case class LongConst(value: Long) extends Expression
  case class Neg(expression: Expression) extends Expression

  case class Array() extends Expression
  case class SpecialRefAsExpr() extends Expression

  trait Statement
  case class For() extends Statement
  case class While() extends Statement
  case class If() extends Statement
  case class Assign() extends Statement
  case class AssignMultiple() extends Statement
  case class Reassign() extends Statement
  case class Return() extends Statement
  case class Lambda() extends Statement
  case class ModelDef() extends Statement
  case class ExprAsStmt() extends Statement
  case class BlockStmt() extends Statement
  case class Match() extends Statement
  case class Print() extends Statement
  case class Println() extends Statement

  case class Case(expression: Expression, block: Block)

  trait Operator

  trait BBinOp extends Operator
  case class And() extends BBinOp
  case class Or() extends BBinOp

  trait ABinOp extends Operator
  case class Add() extends ABinOp
  case class Subtract() extends ABinOp
  case class Multiply() extends ABinOp
  case class Divide() extends ABinOp

  trait RBinOp extends Operator
  case class GreaterEqual() extends RBinOp
  case class Greater() extends RBinOp
  case class LessEqual() extends RBinOp
  case class Less() extends RBinOp
  case class Equal() extends RBinOp


}
