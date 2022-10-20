package com.rahul.order.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.rahul.order.entity.OrderDetail;
import com.rahul.order.repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	private OrderDetailRepository orderepo;

	@Autowired
	public OrderDetailServiceImpl(OrderDetailRepository orderepo) {
		this.orderepo = orderepo;	
	}
	
	@Override
	public List<OrderDetail> getAll() {
		return orderepo.findAll();
	}
	}

