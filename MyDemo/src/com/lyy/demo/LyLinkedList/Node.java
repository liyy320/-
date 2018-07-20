package com.lyy.demo.LyLinkedList;

@SuppressWarnings("unused")
/**
 * LinkedList的节点信息类
 * */
public class Node
{
	private Node   prevNode;	//前置节点
	private Object item;		//元素值
	private Node   nextNode;	//后置节点
	
	//LinkedList节点信息类的有参构造
	public Node(Node prevNode, Object item, Node nextNode) 
	{
		this.prevNode = prevNode;
		this.item     = item;
		this.nextNode = nextNode;
	}
}
