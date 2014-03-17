/**
 */
package org.eclipse.ease.ui.repository.impl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ease.IScriptEngine;
import org.eclipse.ease.Logger;
import org.eclipse.ease.ResourceTools;
import org.eclipse.ease.service.EngineDescription;
import org.eclipse.ease.service.IScriptService;
import org.eclipse.ease.service.ScriptType;
import org.eclipse.ease.ui.console.ScriptConsole;
import org.eclipse.ease.ui.repository.IEntry;
import org.eclipse.ease.ui.repository.IRepositoryPackage;
import org.eclipse.ease.ui.repository.IScript;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ui.PlatformUI;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Script</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.ease.ui.repository.impl.ScriptImpl#getTimestamp <em>Timestamp</em>}</li>
 * <li>{@link org.eclipse.ease.ui.repository.impl.ScriptImpl#getEntry <em>Entry</em>}</li>
 * <li>{@link org.eclipse.ease.ui.repository.impl.ScriptImpl#isUpdatePending <em>Update Pending</em>}</li>
 * <li>{@link org.eclipse.ease.ui.repository.impl.ScriptImpl#getScriptParameters <em>Script Parameters</em>}</li>
 * <li>{@link org.eclipse.ease.ui.repository.impl.ScriptImpl#getUserParameters <em>User Parameters</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ScriptImpl extends LocationImpl implements IScript {
	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final long TIMESTAMP_EDEFAULT = -1L;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected long timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEntry() <em>Entry</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEntry()
	 * @generated
	 * @ordered
	 */
	protected IEntry entry;

	/**
	 * The default value of the '{@link #isUpdatePending() <em>Update Pending</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isUpdatePending()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UPDATE_PENDING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUpdatePending() <em>Update Pending</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isUpdatePending()
	 * @generated
	 * @ordered
	 */
	protected boolean updatePending = UPDATE_PENDING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScriptParameters() <em>Script Parameters</em>}' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getScriptParameters()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> scriptParameters;

	/**
	 * The cached value of the '{@link #getUserParameters() <em>User Parameters</em>}' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getUserParameters()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> userParameters;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScriptImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IRepositoryPackage.Literals.SCRIPT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setTimestamp(final long newTimestamp) {
		long oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IRepositoryPackage.SCRIPT__TIMESTAMP, oldTimestamp, timestamp));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IEntry getEntry() {
		if ((entry != null) && entry.eIsProxy()) {
			InternalEObject oldEntry = (InternalEObject) entry;
			entry = (IEntry) eResolveProxy(oldEntry);
			if (entry != oldEntry) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IRepositoryPackage.SCRIPT__ENTRY, oldEntry, entry));
			}
		}
		return entry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IEntry basicGetEntry() {
		return entry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEntry(final IEntry newEntry) {
		IEntry oldEntry = entry;
		entry = newEntry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IRepositoryPackage.SCRIPT__ENTRY, oldEntry, entry));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isUpdatePending() {
		return updatePending;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setUpdatePending(final boolean newUpdatePending) {
		boolean oldUpdatePending = updatePending;
		updatePending = newUpdatePending;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IRepositoryPackage.SCRIPT__UPDATE_PENDING, oldUpdatePending, updatePending));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<String, String> getScriptParameters() {
		if (scriptParameters == null) {
			scriptParameters = new EcoreEMap<String, String>(IRepositoryPackage.Literals.PARAMETER_MAP, ParameterMapImpl.class, this,
					IRepositoryPackage.SCRIPT__SCRIPT_PARAMETERS);
		}
		return scriptParameters;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<String, String> getUserParameters() {
		if (userParameters == null) {
			userParameters = new EcoreEMap<String, String>(IRepositoryPackage.Literals.PARAMETER_MAP, ParameterMapImpl.class, this,
					IRepositoryPackage.SCRIPT__USER_PARAMETERS);
		}
		return userParameters;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void run() {
		final IScriptService scriptService = (IScriptService) PlatformUI.getWorkbench().getService(IScriptService.class);

		EngineDescription engineDescription = scriptService.getEngine(getType().getName());

		// try to execute
		if (engineDescription != null) {
			IScriptEngine engine = engineDescription.createEngine();

			// create console
			final ScriptConsole console = ScriptConsole.create(engine.getName() + ": " + getPath(), engine);
			engine.setOutputStream(console.getOutputStream());
			engine.setErrorStream(console.getErrorStream());

			// set dummy input parameters. Scripts do not have any, but script source might expect them
			engine.setVariable("argv", new String[0]);
			engine.executeAsync(getContent());
			engine.schedule();

		} else
			Logger.logError("Could not detect script engine for " + this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getName() {
		return getPath().lastSegment();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public IPath getPath() {
		String name = getParameters().get("name");
		if (name != null)
			return new Path(name).makeAbsolute();

		return new Path(getLocation().substring(getEntry().getLocation().length())).makeAbsolute();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID, final NotificationChain msgs) {
		switch (featureID) {
		case IRepositoryPackage.SCRIPT__SCRIPT_PARAMETERS:
			return ((InternalEList<?>) getScriptParameters()).basicRemove(otherEnd, msgs);
		case IRepositoryPackage.SCRIPT__USER_PARAMETERS:
			return ((InternalEList<?>) getUserParameters()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
		switch (featureID) {
		case IRepositoryPackage.SCRIPT__TIMESTAMP:
			return getTimestamp();
		case IRepositoryPackage.SCRIPT__ENTRY:
			if (resolve)
				return getEntry();
			return basicGetEntry();
		case IRepositoryPackage.SCRIPT__UPDATE_PENDING:
			return isUpdatePending();
		case IRepositoryPackage.SCRIPT__SCRIPT_PARAMETERS:
			if (coreType)
				return getScriptParameters();
			else
				return getScriptParameters().map();
		case IRepositoryPackage.SCRIPT__USER_PARAMETERS:
			if (coreType)
				return getUserParameters();
			else
				return getUserParameters().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(final int featureID, final Object newValue) {
		switch (featureID) {
		case IRepositoryPackage.SCRIPT__TIMESTAMP:
			setTimestamp((Long) newValue);
			return;
		case IRepositoryPackage.SCRIPT__ENTRY:
			setEntry((IEntry) newValue);
			return;
		case IRepositoryPackage.SCRIPT__UPDATE_PENDING:
			setUpdatePending((Boolean) newValue);
			return;
		case IRepositoryPackage.SCRIPT__SCRIPT_PARAMETERS:
			((EStructuralFeature.Setting) getScriptParameters()).set(newValue);
			return;
		case IRepositoryPackage.SCRIPT__USER_PARAMETERS:
			((EStructuralFeature.Setting) getUserParameters()).set(newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(final int featureID) {
		switch (featureID) {
		case IRepositoryPackage.SCRIPT__TIMESTAMP:
			setTimestamp(TIMESTAMP_EDEFAULT);
			return;
		case IRepositoryPackage.SCRIPT__ENTRY:
			setEntry((IEntry) null);
			return;
		case IRepositoryPackage.SCRIPT__UPDATE_PENDING:
			setUpdatePending(UPDATE_PENDING_EDEFAULT);
			return;
		case IRepositoryPackage.SCRIPT__SCRIPT_PARAMETERS:
			getScriptParameters().clear();
			return;
		case IRepositoryPackage.SCRIPT__USER_PARAMETERS:
			getUserParameters().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(final int featureID) {
		switch (featureID) {
		case IRepositoryPackage.SCRIPT__TIMESTAMP:
			return timestamp != TIMESTAMP_EDEFAULT;
		case IRepositoryPackage.SCRIPT__ENTRY:
			return entry != null;
		case IRepositoryPackage.SCRIPT__UPDATE_PENDING:
			return updatePending != UPDATE_PENDING_EDEFAULT;
		case IRepositoryPackage.SCRIPT__SCRIPT_PARAMETERS:
			return (scriptParameters != null) && !scriptParameters.isEmpty();
		case IRepositoryPackage.SCRIPT__USER_PARAMETERS:
			return (userParameters != null) && !userParameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eInvoke(final int operationID, final EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
		case IRepositoryPackage.SCRIPT___RUN:
			run();
			return null;
		case IRepositoryPackage.SCRIPT___GET_NAME:
			return getName();
		case IRepositoryPackage.SCRIPT___GET_PATH:
			return getPath();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (timestamp: ");
		result.append(timestamp);
		result.append(", updatePending: ");
		result.append(updatePending);
		result.append(')');
		return result.toString();
	}

	@Override
	public Map<String, String> getParameters() {
		// first merge script parameters
		Map<String, String> parameters = new HashMap<String, String>(getScriptParameters().map());

		// now apply user parameters, as they have higher priority
		parameters.putAll(getUserParameters().map());

		return parameters;
	}

	@Override
	public ScriptType getType() {
		final IScriptService scriptService = (IScriptService) PlatformUI.getWorkbench().getService(IScriptService.class);
		ScriptType type = null;

		// script type as provided in metadata
		String identifier = getParameters().get("script-type");
		if (identifier != null)
			type = scriptService.getAvailableScriptTypes().get(identifier);

		// script type from file
		if (type == null) {
			Object content = getContent();
			if ((content instanceof IFile) && (((IFile) content).exists()))
				type = ResourceTools.getScriptType((IFile) content);

			else if ((content instanceof File) && (((File) content).exists()))
				type = ResourceTools.getScriptType((File) content);

			// TODO get content type from raw file data (read file)
		}

		return type;
	}
} // ScriptImpl
