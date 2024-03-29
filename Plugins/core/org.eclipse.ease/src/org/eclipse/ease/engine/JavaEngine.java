package org.eclipse.ease.engine;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.ease.FileTrace;
import org.eclipse.ease.IExecutionListener;
import org.eclipse.ease.IScriptEngine;
import org.eclipse.ease.ScriptResult;
import org.eclipse.ease.modules.IEnvironment;
import org.eclipse.ease.modules.NativeEnvironment;
import org.eclipse.ease.service.EngineDescription;

public class JavaEngine implements IScriptEngine {

	private PrintStream fOutStream;
	private PrintStream fErrorStream;
	private InputStream fInStream;

	private final Map<String, Object> fVariables = new HashMap<String, Object>();
	private final IEnvironment fEnvironment;

	public JavaEngine() {
		fEnvironment = new NativeEnvironment();
		((NativeEnvironment) fEnvironment).initialize(this, fEnvironment);
	}

	@Override
	public ScriptResult executeAsync(final Object content) {
		throw new RuntimeException("not supported");
	}

	@Override
	public ScriptResult executeSync(final Object content) throws InterruptedException {
		throw new RuntimeException("not supported");
	}

	@Override
	public Object inject(final Object content) {
		throw new RuntimeException("not supported");
	}

	@Override
	public Object getExecutedFile() {
		return null;
	}

	@Override
	public void setTerminateOnIdle(final boolean terminate) {
		// do nothing
	}

	@Override
	public boolean getTerminateOnIdle() {
		return false;
	}

	@Override
	public void schedule() {
		// do nothing
	}

	@Override
	public void terminate() {
		// do nothing
	}

	@Override
	public void terminateCurrent() {
		// do nothing
	}

	@Override
	public void addExecutionListener(final IExecutionListener listener) {
		// do nothing
	}

	@Override
	public void removeExecutionListener(final IExecutionListener listener) {
		// do nothing
	}

	@Override
	public void setIsUI(final boolean isUI) {
		// do nothing
	}

	@Override
	public boolean isUI() {
		throw new RuntimeException("not supported");
	}

	@Override
	public void reset() {
		// do nothing
	}

	@Override
	public boolean isIdle() {
		return true;
	}

	@Override
	public FileTrace getFileTrace() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setVariable(final String name, final Object content) {
		fVariables.put(name, content);
	}

	@Override
	public Object getVariable(final String name) {
		return fVariables.get(name);
	}

	@Override
	public boolean hasVariable(final String name) {
		return fVariables.containsKey(name);
	}

	@Override
	public String getSaveVariableName(final String name) {
		return name;
	}

	@Override
	public PrintStream getOutputStream() {
		return (fOutStream != null) ? fOutStream : System.out;
	}

	@Override
	public void setOutputStream(final OutputStream outputStream) {
		if (outputStream instanceof PrintStream)
			fOutStream = (PrintStream) outputStream;

		else
			fOutStream = new PrintStream(outputStream);
	}

	@Override
	public InputStream getInputStream() {
		return (fInStream != null) ? fInStream : System.in;
	}

	@Override
	public void setInputStream(final InputStream inputStream) {
		fInStream = inputStream;
	}

	@Override
	public PrintStream getErrorStream() {
		return (fErrorStream != null) ? fErrorStream : System.err;
	}

	@Override
	public void setErrorStream(final OutputStream errorStream) {
		if (errorStream instanceof PrintStream)
			fErrorStream = (PrintStream) errorStream;

		else
			fErrorStream = new PrintStream(errorStream);
	}

	public IEnvironment getEnvironment() {
		return fEnvironment;
	}

	@Override
	public EngineDescription getDescription() {
		throw new RuntimeException("not supported");
	}

	@Override
	public Object removeVariable(final String name) {
		return getVariables().remove(name);
	}

	@Override
	public Map<String, Object> getVariables() {
		return fVariables;
	}

	@Override
	public void registerJar(final URL url) {
		throw new RuntimeException("not supported");
	}
}
