package org.cipango.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.sip.Proxy;
import javax.servlet.sip.ProxyBranch;
import javax.servlet.sip.Rel100Exception;
import javax.servlet.sip.SipServletRequest;
import javax.servlet.sip.SipServletResponse;

import org.cipango.sip.SipFields;
import org.cipango.sip.SipHeader;

public class SipResponse extends SipMessage implements SipServletResponse
{
	private SipRequest _request;
	private int _status;
	private String _reason;
	
	public SipResponse(SipRequest request, int status, String reason)
	{
		_request = request;
		if (status >= 200)
			_request.setCommitted(true);
		
		setStatus(status, reason);
		
		SipFields requestFields = request.getFields();
		_fields.copy(requestFields, SipHeader.VIA);
		_fields.copy(requestFields, SipHeader.FROM);
		_fields.copy(requestFields, SipHeader.TO);
		_fields.copy(requestFields, SipHeader.CALL_ID);
		_fields.copy(requestFields, SipHeader.CSEQ);
		
		if (status < 300)
			_fields.copy(requestFields, SipHeader.RECORD_ROUTE);
	}
	
	/**
	 * @see SipServletResponse#send()
	 */
	public void send() throws IOException
	{
		send(false);
	}
	
	protected void send(boolean reliable) throws IOException
	{
		if (isCommitted())
			throw new IllegalStateException("response is committed");
		
		// TODO scope
		
	}
	
	public boolean isRequest()
	{
		return false;
	}
	
	@Override
	public void setCharacterEncoding(String encoding)
	{
		/*
		 * Because of a change in Servlet spec 2.4 the setCharacterEncoding() 
		 * does NOT throw the java.io.UnsupportedEncodingException as derived 
		 * from SipServletMessage.setCharacterEncoding(String) but inherits 
		 * a more generic setCharacterEncoding() method from the 
		 * javax.servlet.ServletResponse. 
		 */
		//_characterEncoding = encoding;
	}
	
	public void setBufferSize(int size) 
	{
		
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SipServletRequest createAck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SipServletRequest createPrack() throws Rel100Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<String> getChallengeRealms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proxy getProxy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProxyBranch getProxyBranch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReasonPhrase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SipServletRequest getRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see SipServletResponse#getStatus()
	 */
	public int getStatus() 
	{
		return _status;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBranchResponse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendReliably() throws Rel100Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void setStatus(int status) 
	{
		setStatus(status, null);		
	}

	/**
	 * @see SipServletResponse#setStatus(int, String)
	 */
	public void setStatus(int status, String reason) 
	{
		if (status < 100 || status >= 700) 
    		throw new IllegalArgumentException("Invalid status-code: " + status);
    	    	
		_status = status;
		_reason = reason;
	}

	@Override
	protected boolean canSetContact() {
		// TODO Auto-generated method stub
		return false;
	}

}
