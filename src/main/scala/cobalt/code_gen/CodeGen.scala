package cobalt.code_gen

import cobalt.ast.AST.{ClassModel, Module, Statement}

import scala.tools.asm._
import scala.tools.asm.Opcodes;

object CodeGen
{

  val version = 49

  def genCode(module: Module): Array[Byte] =
  {
    val classWriter = new ClassWriter(0)

    module.models.foreach(x => genCode(classWriter, x))
    classWriter.visitEnd()
    classWriter.toByteArray
  }

  def genCode(classWriter: ClassWriter, statement: Statement): Unit =
  {
    statement match
    {
      case classB: ClassModel => {
        classWriter.visit(version, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, classB.name.value, null, "java/lang/Object", null)
        val mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V")
        mv.visitInsn(Opcodes.RETURN)
        mv.visitMaxs(1, 1)
        mv.visitEnd()
      }

    }
  }

  @throws[Exception]
  def dump: Array[Byte] =
  {
    val cw = new ClassWriter(0)
    var mv: MethodVisitor = null

    cw.visit(49, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, "Hello", null, "java/lang/Object", null)
    cw.visitSource("Hello.java", null)
    mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)
    mv.visitVarInsn(Opcodes.ALOAD, 0)
    mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V")
    mv.visitInsn(Opcodes.RETURN)
    mv.visitMaxs(1, 1)
    mv.visitEnd()

    mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null)
    mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
    mv.visitLdcInsn("hello")
    mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V")
    mv.visitInsn(Opcodes.RETURN)
    mv.visitMaxs(2, 1)
    mv.visitEnd()

    cw.visitEnd()
    cw.toByteArray
  }
}
