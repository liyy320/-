package com.lyy.demo;

import java.util.Arrays;

/**
 * ArrayList�ײ����
 * */
public class LyArrayList
{

	/**ArrayList�ײ�ʹ������ķ�ʽʵ��*/
	private Object [] elementData;

	/**�����ʼ����СΪ10*/
	private static final int DEFAULT_CAPACITY = 10;

	/**��¼������Ԫ�صĸ���*/
	private int size;

	/**Ĭ�ϳ�ʼ�������СΪ10*/
	public LyArrayList(){this(DEFAULT_CAPACITY);}

	/**��ʼ��ָ����С������*/
	public LyArrayList(int initialCapacity)
	{
		/*�ж�ָ���Ĵ�С����Ϊ0*/
		if(initialCapacity < 0)
		{
			throw new IllegalArgumentException("��ʼ��������С��0��" + initialCapacity);
		}

		/*��ʼ������*/
		elementData = new Object[initialCapacity];
	}

	/**�������Ԫ��*/
	public void add(Object o)
	{
		/*�ж�ʵ�ʴ�ŵ������Ƿ����elementData�������������������*/
		ensureExplicitCapacity(size + 1);

		/*�����±���и�ֵ*/
		elementData[size++] = o;

	}
	
	/**��Ԫ����ӵ�ָ����λ��*/
	public void add(int index, Object o)
	{
		ensureExplicitCapacity(size + 1);

		System.arraycopy(elementData, index, elementData, index + 1, size - index);

		elementData[index] = o;

		size++;
	}

	/**�ж����������Ƿ񳬳���������������*/
	private void ensureExplicitCapacity(int minCapacity)
	{
		/*�ж����鳤���Ƿ񳬳�*/
		if(size == elementData.length)
		{
			int oldCapacity = elementData.length;
			
			int newCapacity = oldCapacity + (oldCapacity >> 1);

			if(newCapacity - oldCapacity < 0) newCapacity = minCapacity;

			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	/**�����±��ȡԪ�صķ���*/
	public Object get(int index){rangeCheck(index); return elementData[index];}
	
	/**�����±��Ƴ�Ԫ�ز��ҷ����Ƴ���Ԫ��*/
	public Object remove(int index)
	{
		
		Object o = get(index);
		
		int numMoved = size - index - 1;
		
		if(numMoved > 0)
		{
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
			
			elementData[--size] = null;
		}
		
		return o;
	}
	
	/**����Ԫ���Ƴ�*/
	public boolean remove(Object o)
	{
		
		for(int i=0;i<elementData.length;i++)
		{
			if(elementData[i].equals(o))
			{
				remove(i);
				
				return true;
			}
		}
		
		return false;
	}
	
	
	
	//��������Ƿ�Խ��
	private void rangeCheck(int index)
	{
		if(index >= size)throw new IndexOutOfBoundsException("����������");
	}
	
	//��ȡ����Ԫ�ظ���
	public int getSize(){return size;}
	
}
