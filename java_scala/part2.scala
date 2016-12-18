trait Ordered[T]{
  def compare(other: T): Int;
  def <(other: T): Boolean;
  def <=(other:T) = <(other) || ==(other)
  def >(other:T) = !(<(other)) && !=(other)
  def >=(other: T) = !(<(other))
}
class OInt(value:Integer) extends Ordered[OInt]{
  val myValue = value
  def compare (obj2:OInt): Int = {
		if (this.value < obj2.myValue) -1
	    else if (this.value == obj2.myValue) 0
        else 1
  }

  override def toString={
    value+""
  }

}

abstract class OTree[T <: Ordered[T]] extends Ordered[OTree[T]]

case class ONode[T <: Ordered[T]](v: T, c: List[OTree[T]]) extends OTree[T]{
  // case class ONode[T <: Ordered[T]](v: T, c: OTree[T]*) extends OTree[T]{
  def compare(other: OTree[T]) = other match {
  // case ONode(ov, oc) => (v < ov) && (c, oc).zipped.forall( (new OInt(c)).compare(new OInt(oc)))
    case ONode(ov, oc) if(compareLists(c, oc)==0) => (v < ov)
    case ONode(ov, oc) if(compareLists(c, oc)==-1) => (v < ov) && false
    case ONode(ov, oc) if(compareLists(c, oc)==1) => (v < ov) && true
    case _ => false
  }

  def compareLists(L1: List[OTree[T]], L2: List[OTree[T]]):Integer = (L1, L2)  match {
    case (List(), List()) => 0
    case (List(), _) => -1
    case (_, List()) => 1
    case(x::xs,y::ys) if(x.compare(y)==0) => compareLists(xs,ys)
    case(x::xs,y::ys) if(x.compare(y)==-1) => -1
    case(x::xs,y::ys) if(x.compare(y)==1) => 1
  }
  override def toString() = v + "(" + c + ")"
}
// delta type 'a mylist = Nil | Cons of 'a*'a mylist
case class OLeaf[T <: Ordered[T]](v:T) extends OTree[T]{
  def <(other: OTree[T]) = other match {
    case OLeaf(ov) => v < ov
    case _ => false
  }
  override def toString() = v + ""
}

object part2{
  /*
  // def compareTrees (tree1:OTree[Ordered[T]], tree2:OTree[Ordered[T]]) = {
  def compareTrees[T <:Ordered[T]] (tree1:T, tree2:T) = {
    if (tree1 < tree2){
      println("tree2")
    }
    else if (tree1 == tree2){
      println("same")
    }
    else {
      println(tree1)
    }

  }*/
  def test() {
    val tree1 = ONode(List(OLeaf(new OInt(6))))

    val tree2 = ONode(List(OLeaf(new OInt(3)),
               OLeaf(new OInt(4)),
               ONode(List(OLeaf(new OInt(5)))),
               ONode(List(OLeaf(new OInt(6)),
                      OLeaf(new OInt(7))))));

    val treeTree1: OTree[OTree[OInt]] =
      ONode(List(OLeaf(OLeaf(new OInt(1)))))

    val treeTree2: OTree[OTree[OInt]] =
      ONode(List(OLeaf(OLeaf(new OInt(1))),
         OLeaf(ONode(List(OLeaf(new OInt(2)),
                  OLeaf(new OInt(2)))))))


    print("tree1: ")
    println(tree1)
    print("tree2: ")
    println(tree2)
    print("treeTree1: ")
    println(treeTree1)
    print("treeTree2: ")
    println(treeTree2)
    print("Comparing tree1 and tree2: ")
    compareTrees(tree1, tree2)
    print("Comparing tree2 and tree2: ")
    compareTrees(tree2, tree2)
    print("Comparing tree2 and tree1: ")
    compareTrees(tree2, tree1)
    print("Comparing treeTree1 and treeTree2: ")
    compareTrees(treeTree1, treeTree2)
    print("Comparing treeTree2 and treeTree2: ")
    compareTrees(treeTree2, treeTree2)
    print("Comparing treeTree2 and treeTree1: ")
    compareTrees(treeTree2, treeTree1)

  }

  def main(args: Array[String]): Unit = {
    //val a = new OInt(1)
    // a.compare
    println(new OInt(7).toString)
    println((new OInt(5)).compare(new OInt(6)))
    // val myTree = ONode(List(OLeaf(new OInt(3)), Oleaf(new OInt(4))))
    // println(myTree)
  }
}
