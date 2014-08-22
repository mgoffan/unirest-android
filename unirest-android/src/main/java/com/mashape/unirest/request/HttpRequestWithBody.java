/*
The MIT License

Copyright (c) 2013 Mashape (http://mashape.com)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.mashape.unirest.request;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import com.mashape.unirest.http.HttpMethod;
import com.mashape.unirest.request.body.MultipartBody;
import com.mashape.unirest.request.body.RequestBodyEntity;

public class HttpRequestWithBody extends HttpRequest {

	public HttpRequestWithBody(HttpMethod method, String url) {
		super(method, url);
	}

	@Override
	public HttpRequestWithBody header(String name, Object value) {
		return (HttpRequestWithBody) super.header(name, value);
	}
	
	@Override
	public HttpRequestWithBody headers(Map<String, Object> headers) {
		return (HttpRequestWithBody) super.headers(headers);
	}
	
	public HttpRequestWithBody basicAuth(String username, String password) {
		super.basicAuth(username, password);
		return this;
	}

	public MultipartBody field(String name, Object value) {
		MultipartBody body =  new MultipartBody(this);

        if(null != value)
            body.field(name, value.toString());

		this.body = body;
		return body;
	}
	
	public MultipartBody field(String name, File file) {
		MultipartBody body =  new MultipartBody(this);

        if(null != file)
            body.field(name, file);

		this.body = body;
		return body;
	}
	
	public MultipartBody fields(Map<String, Object> parameters) {
		MultipartBody body =  new MultipartBody(this);
		if (parameters != null) {
			for(Entry<String, Object> param : parameters.entrySet()) {
                Object value = param.getValue();
                if(null == value)
                    continue;

				if (value instanceof File) {
					body.field(param.getKey(), (File)value);
				} else {
					body.field(param.getKey(), value.toString());
				}
			}
		}
		this.body = body;
		return body;
	}

	public RequestBodyEntity body(String body) {
		RequestBodyEntity b =  new RequestBodyEntity(this).body(body);
		this.body = b;
		return b;
	}
	
}
