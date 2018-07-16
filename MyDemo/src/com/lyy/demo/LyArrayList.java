package com.lyy.demo;

import java.util.Arrays;

/**
 * ArrayList底层代码
 * */
public class LyArrayList
{

	/**ArrayList底层使用数组的方式实现*/
	private Object [] elementData;

	/**数组初始化大小为10*/
	private static final int DEFAULT_CAPACITY = 10;

	/**记录数组中元素的个数*/
	private int size;

	/**默认初始化数组大小为10*/
	public LyArrayList(){this(DEFAULT_CAPACITY);}

	/**初始化指定大小的数组*/
	public LyArrayList(int initialCapacity)
	{
		/*判断指定的大小不能为0*/
		if(initialCapacity < 0)
		{
			throw new IllegalArgumentException("初始容量不能小于0：" + initialCapacity);
		}

		/*初始化数组*/
		elementData = new Object[initialCapacity];
	}

	/**添加数组元素*/
	public void add(Object o)
	{
		/*判断实际存放的数量是否大于elementData的容量，否则进行扩容*/
		ensureExplicitCapacity(size + 1);

		/*根据下标进行赋值*/
		elementData[size++] = o;

	}
	
	/**将元素添加到指定的位置*/
	public void add(int index, Object o)
	{
		ensureExplicitCapacity(size + 1);

		System.arraycopy(elementData, index, elementData, index + 1, size - index);

		elementData[index] = o;

		size++;
	}

	/**判断数组容量是否超出，进行数组扩容*/
	private void ensureExplicitCapacity(int minCapacity)
	{
		/*判断数组长度是否超出*/
		if(size == elementData.length)
		{
			int oldCapacity = elementData.length;
			
			int newCapacity = oldCapacity + (oldCapacity >> 1);

			if(newCapacity - oldCapacity < 0) newCapacity = minCapacity;

			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	/**根据下标获取元素的方法*/
	public Object get(int index){rangeCheck(index); return elementData[index];}
	
	/**根据下标移除元素并且返回移除的元素*/
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
	
	/**根据元素移除*/
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
	
	
	
	//检测数组是否越界
	private void rangeCheck(int index)
	{
		if(index >= size)throw new IndexOutOfBoundsException("篮子满啦！");
	}
	
	//获取数组元素个数
	public int getSize(){return size;}
	
}
