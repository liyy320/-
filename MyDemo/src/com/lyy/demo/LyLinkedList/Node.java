package com.lyy.demo.LyLinkedList;

@SuppressWarnings("unused")
/**
 * LinkedList�Ľڵ���Ϣ��
 * */
public class Node
{
	private Node   prevNode;	//ǰ�ýڵ�
	private Object item;		//Ԫ��ֵ
	private Node   nextNode;	//���ýڵ�
	
	//LinkedList�ڵ���Ϣ����вι���
	public Node(Node prevNode, Object item, Node nextNode) 
	{
		this.prevNode = prevNode;
		this.item     = item;
		this.nextNode = nextNode;
	}
}
