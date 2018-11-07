package br.com.b2w.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class CustomPageImpl<T> extends PageImpl<T> {
    private static final long serialVersionUID = 1L;
    private int number;
    private int size;
    private int totalPages;
    private int numberOfElements;
    private long totalElements;
    private Integer count;
    private String next;
    private String last;
    private List<T> content;
    private Sort sort;

    public CustomPageImpl() {
        super(new ArrayList<>());
    }

    @Override
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    @Override
    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Page<T> pageImpl() {
        return new PageImpl<T>(getContent(), PageRequest.of(getNumber(),
                getSize(), getSort()), getTotalElements());
    }

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}