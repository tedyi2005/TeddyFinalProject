package com.example.all_inone_app;

import android.graphics.Bitmap;

public class News{

	String title, publisher, publisherDate, redirectUrl;
	Bitmap newsImage;

	public News(String title, String publisher, Bitmap newsImage,String publisherDate,String redirectUrl) {
		this.title = title;
		this.publisher = publisher;
		this.newsImage = newsImage;
		this.publisherDate = publisherDate;
		this.redirectUrl  = redirectUrl;

	}
}
