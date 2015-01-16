package com.f2m02dr0.io.gpio.impl;

import com.f2m02dr0.concurrent.ExecutorServiceFactory;
import com.f2m02dr0.io.gpio.GpioController;
import com.f2m02dr0.io.gpio.GpioFactory;
import com.f2m02dr0.io.gpio.GpioPin;
import com.f2m02dr0.io.gpio.GpioPinAnalog;
import com.f2m02dr0.io.gpio.GpioPinAnalogInput;
import com.f2m02dr0.io.gpio.GpioPinAnalogOutput;
import com.f2m02dr0.io.gpio.GpioPinDigital;
import com.f2m02dr0.io.gpio.GpioPinDigitalInput;
import com.f2m02dr0.io.gpio.GpioPinDigitalMultipurpose;
import com.f2m02dr0.io.gpio.GpioPinDigitalOutput;
import com.f2m02dr0.io.gpio.GpioPinInput;
import com.f2m02dr0.io.gpio.GpioPinPwmOutput;
import com.f2m02dr0.io.gpio.GpioPinShutdown;
import com.f2m02dr0.io.gpio.GpioProvider;
import com.f2m02dr0.io.gpio.Pin;
import com.f2m02dr0.io.gpio.PinMode;
import com.f2m02dr0.io.gpio.PinPullResistance;
import com.f2m02dr0.io.gpio.PinState;
import com.f2m02dr0.io.gpio.event.GpioPinListener;
import com.f2m02dr0.io.gpio.exception.GpioPinExistsException;
import com.f2m02dr0.io.gpio.exception.GpioPinNotProvisionedException;
import com.f2m02dr0.io.gpio.trigger.GpioTrigger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GpioControllerImpl
  implements GpioController
{
  private final List<GpioPin> pins = Collections.synchronizedList(new ArrayList());
  private final GpioProvider defaultProvider;
  private boolean isshutdown = false;
  
  public GpioControllerImpl()
  {
    this(GpioFactory.getDefaultProvider());
  }
  
  public GpioControllerImpl(GpioProvider provider)
  {
    this.defaultProvider = provider;
    

    Runtime.getRuntime().addShutdownHook(new ShutdownHook(null));
  }
  
  public Collection<GpioPin> getProvisionedPins()
  {
    return this.pins;
  }
  
  public void unexportAll()
  {
    for (GpioPin pin : this.pins) {
      if (pin.isExported()) {
        pin.unexport();
      }
    }
  }
  
  public void export(PinMode mode, GpioPin... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.export(mode);
    }
  }
  
  public boolean isExported(GpioPin... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(pin)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      if (!p.isExported()) {
        return false;
      }
    }
    return true;
  }
  
  public void unexport(GpioPin... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.unexport();
    }
  }
  
  public PinMode getMode(GpioPin pin)
  {
    if (!this.pins.contains(pin)) {
      throw new GpioPinNotProvisionedException(pin.getPin());
    }
    return pin.getMode();
  }
  
  public boolean isMode(PinMode mode, GpioPin... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPin p : pin) {
      if (!p.isMode(mode)) {
        return false;
      }
    }
    return true;
  }
  
  public void setMode(PinMode mode, GpioPin... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(pin)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.setMode(mode);
    }
  }
  
  public void setPullResistance(PinPullResistance resistance, GpioPin... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.setPullResistance(resistance);
    }
  }
  
  public PinPullResistance getPullResistance(GpioPin pin)
  {
    if (!this.pins.contains(pin)) {
      throw new GpioPinNotProvisionedException(pin.getPin());
    }
    return pin.getPullResistance();
  }
  
  public boolean isPullResistance(PinPullResistance resistance, GpioPin... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      if (!p.isPullResistance(resistance)) {
        return false;
      }
    }
    return true;
  }
  
  public void high(GpioPinDigitalOutput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinDigitalOutput p : pin)
    {
      if (!this.pins.contains(pin)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.high();
    }
  }
  
  public void low(GpioPinDigitalOutput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinDigitalOutput p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.low();
    }
  }
  
  public boolean isHigh(GpioPinDigital... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinDigital p : pin) {
      if (p.isLow()) {
        return false;
      }
    }
    return true;
  }
  
  public boolean isLow(GpioPinDigital... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinDigital p : pin) {
      if (p.isHigh()) {
        return false;
      }
    }
    return true;
  }
  
  public void toggle(GpioPinDigitalOutput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinDigitalOutput p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.toggle();
    }
  }
  
  public void pulse(long milliseconds, GpioPinDigitalOutput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinDigitalOutput p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.pulse(milliseconds);
    }
  }
  
  public void setState(PinState state, GpioPinDigitalOutput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinDigitalOutput p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.setState(state);
    }
  }
  
  public void setState(boolean state, GpioPinDigitalOutput... pin)
  {
    setState(state ? PinState.HIGH : PinState.LOW, pin);
  }
  
  public PinState getState(GpioPinDigital pin)
  {
    if (!this.pins.contains(pin)) {
      throw new GpioPinNotProvisionedException(pin.getPin());
    }
    return pin.getState();
  }
  
  public boolean isState(PinState state, GpioPinDigital... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinDigital p : pin) {
      if (!p.isState(state)) {
        return false;
      }
    }
    return true;
  }
  
  public void setValue(double value, GpioPinAnalogOutput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinAnalogOutput p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.setValue(value);
    }
  }
  
  public double getValue(GpioPinAnalog pin)
  {
    return pin.getValue();
  }
  
  public synchronized void addListener(GpioPinListener listener, GpioPinInput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinInput p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.addListener(new GpioPinListener[] { listener });
    }
  }
  
  public synchronized void addListener(GpioPinListener[] listeners, GpioPinInput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinListener listener : listeners) {
      addListener(listener, pin);
    }
  }
  
  public synchronized void removeListener(GpioPinListener listener, GpioPinInput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinInput p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.removeListener(new GpioPinListener[] { listener });
    }
  }
  
  public synchronized void removeListener(GpioPinListener[] listeners, GpioPinInput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinListener listener : listeners) {
      removeListener(listener, pin);
    }
  }
  
  public synchronized void removeAllListeners()
  {
    for (GpioPin pin : this.pins) {
      if ((pin instanceof GpioPinInput)) {
        ((GpioPinInput)pin).removeAllListeners();
      }
    }
  }
  
  public synchronized void addTrigger(GpioTrigger trigger, GpioPinInput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinInput p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.addTrigger(new GpioTrigger[] { trigger });
    }
  }
  
  public synchronized void addTrigger(GpioTrigger[] triggers, GpioPinInput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioTrigger trigger : triggers) {
      addTrigger(trigger, pin);
    }
  }
  
  public synchronized void removeTrigger(GpioTrigger trigger, GpioPinInput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioPinInput p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.removeTrigger(new GpioTrigger[] { trigger });
    }
  }
  
  public synchronized void removeTrigger(GpioTrigger[] triggers, GpioPinInput... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (GpioTrigger trigger : triggers) {
      removeTrigger(trigger, pin);
    }
  }
  
  public synchronized void removeAllTriggers()
  {
    for (GpioPin pin : this.pins) {
      if ((pin instanceof GpioPinInput)) {
        ((GpioPinInput)pin).removeAllTriggers();
      }
    }
  }
  
  public GpioPin provisionPin(GpioProvider provider, Pin pin, PinMode mode)
  {
    return provisionPin(provider, pin, pin.getName(), mode);
  }
  
  public GpioPin provisionPin(GpioProvider provider, Pin pin, String name, PinMode mode)
  {
    for (GpioPin p : this.pins) {
      if ((p.getProvider().equals(provider)) && (p.getPin().equals(pin))) {
        throw new GpioPinExistsException(pin);
      }
    }
    GpioPin gpioPin = new GpioPinImpl(this, provider, pin);
    if (name != null) {
      gpioPin.setName(name);
    }
    gpioPin.export(mode);
    

    this.pins.add(gpioPin);
    

    return gpioPin;
  }
  
  public GpioPin provisionPin(Pin pin, String name, PinMode mode)
  {
    return provisionPin(this.defaultProvider, pin, name, mode);
  }
  
  public GpioPin provisionPin(Pin pin, PinMode mode)
  {
    return provisionPin(this.defaultProvider, pin, mode);
  }
  
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, String name, PinMode mode)
  {
    return (GpioPinDigitalMultipurpose)provisionPin(provider, pin, name, mode);
  }
  
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, PinMode mode)
  {
    return (GpioPinDigitalMultipurpose)provisionPin(provider, pin, mode);
  }
  
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, String name, PinMode mode)
  {
    return provisionDigitalMultipurposePin(this.defaultProvider, pin, name, mode);
  }
  
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, PinMode mode)
  {
    return provisionDigitalMultipurposePin(this.defaultProvider, pin, mode);
  }
  
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, PinMode mode, PinPullResistance resistance)
  {
    return provisionDigitalMultipurposePin(provider, pin, pin.getName(), mode, resistance);
  }
  
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider provider, Pin pin, String name, PinMode mode, PinPullResistance resistance)
  {
    GpioPinDigitalMultipurpose gpioPin = provisionDigitalMultipurposePin(provider, pin, name, mode);
    if (resistance != null) {
      gpioPin.setPullResistance(resistance);
    }
    return gpioPin;
  }
  
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, String name, PinMode mode, PinPullResistance resistance)
  {
    return provisionDigitalMultipurposePin(this.defaultProvider, pin, name, mode, resistance);
  }
  
  public GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin pin, PinMode mode, PinPullResistance resistance)
  {
    return provisionDigitalMultipurposePin(this.defaultProvider, pin, mode, resistance);
  }
  
  public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin, String name)
  {
    return (GpioPinDigitalInput)provisionPin(provider, pin, name, PinMode.DIGITAL_INPUT);
  }
  
  public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin)
  {
    return (GpioPinDigitalInput)provisionPin(provider, pin, PinMode.DIGITAL_INPUT);
  }
  
  public GpioPinDigitalInput provisionDigitalInputPin(Pin pin, String name)
  {
    return provisionDigitalInputPin(this.defaultProvider, pin, name);
  }
  
  public GpioPinDigitalInput provisionDigitalInputPin(Pin pin)
  {
    return provisionDigitalInputPin(this.defaultProvider, pin);
  }
  
  public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin, PinPullResistance resistance)
  {
    return provisionDigitalInputPin(provider, pin, pin.getName(), resistance);
  }
  
  public GpioPinDigitalInput provisionDigitalInputPin(GpioProvider provider, Pin pin, String name, PinPullResistance resistance)
  {
    GpioPinDigitalInput gpioPin = provisionDigitalInputPin(provider, pin, name);
    if (resistance != null) {
      gpioPin.setPullResistance(resistance);
    }
    return gpioPin;
  }
  
  public GpioPinDigitalInput provisionDigitalInputPin(Pin pin, String name, PinPullResistance resistance)
  {
    return provisionDigitalInputPin(this.defaultProvider, pin, name, resistance);
  }
  
  public GpioPinDigitalInput provisionDigitalInputPin(Pin pin, PinPullResistance resistance)
  {
    return provisionDigitalInputPin(this.defaultProvider, pin, resistance);
  }
  
  public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin, String name)
  {
    return (GpioPinDigitalOutput)provisionPin(provider, pin, name, PinMode.DIGITAL_OUTPUT);
  }
  
  public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin)
  {
    return (GpioPinDigitalOutput)provisionPin(provider, pin, PinMode.DIGITAL_OUTPUT);
  }
  
  public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin, String name)
  {
    return provisionDigitalOutputPin(this.defaultProvider, pin, name);
  }
  
  public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin)
  {
    return provisionDigitalOutputPin(this.defaultProvider, pin);
  }
  
  public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin, PinState defaultState)
  {
    return provisionDigitalOutputPin(provider, pin, pin.getName(), defaultState);
  }
  
  public GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider provider, Pin pin, String name, PinState defaultState)
  {
    GpioPinDigitalOutput gpioPin = provisionDigitalOutputPin(provider, pin, name);
    if (defaultState != null) {
      gpioPin.setState(defaultState);
    }
    return gpioPin;
  }
  
  public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin, String name, PinState defaultState)
  {
    return provisionDigitalOutputPin(this.defaultProvider, pin, name, defaultState);
  }
  
  public GpioPinDigitalOutput provisionDigitalOutputPin(Pin pin, PinState defaultState)
  {
    return provisionDigitalOutputPin(this.defaultProvider, pin, defaultState);
  }
  
  public GpioPinAnalogInput provisionAnalogInputPin(GpioProvider provider, Pin pin, String name)
  {
    return (GpioPinAnalogInput)provisionPin(provider, pin, name, PinMode.ANALOG_INPUT);
  }
  
  public GpioPinAnalogInput provisionAnalogInputPin(GpioProvider provider, Pin pin)
  {
    return (GpioPinAnalogInput)provisionPin(provider, pin, PinMode.ANALOG_INPUT);
  }
  
  public GpioPinAnalogInput provisionAnalogInputPin(Pin pin, String name)
  {
    return provisionAnalogInputPin(this.defaultProvider, pin, name);
  }
  
  public GpioPinAnalogInput provisionAnalogInputPin(Pin pin)
  {
    return provisionAnalogInputPin(this.defaultProvider, pin);
  }
  
  public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin, String name)
  {
    return (GpioPinAnalogOutput)provisionPin(provider, pin, name, PinMode.ANALOG_OUTPUT);
  }
  
  public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin)
  {
    return (GpioPinAnalogOutput)provisionPin(provider, pin, PinMode.ANALOG_OUTPUT);
  }
  
  public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin, String name)
  {
    return provisionAnalogOutputPin(this.defaultProvider, pin, name);
  }
  
  public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin)
  {
    return provisionAnalogOutputPin(this.defaultProvider, pin);
  }
  
  public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin, double defaultValue)
  {
    return provisionAnalogOutputPin(provider, pin, pin.getName(), defaultValue);
  }
  
  public GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider provider, Pin pin, String name, double defaultValue)
  {
    GpioPinAnalogOutput gpioPin = provisionAnalogOutputPin(provider, pin, name);
    

    gpioPin.setValue(defaultValue);
    

    return gpioPin;
  }
  
  public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin, String name, double defaultValue)
  {
    return provisionAnalogOutputPin(this.defaultProvider, pin, name, defaultValue);
  }
  
  public GpioPinAnalogOutput provisionAnalogOutputPin(Pin pin, double defaultValue)
  {
    return provisionAnalogOutputPin(this.defaultProvider, pin, defaultValue);
  }
  
  public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin, String name)
  {
    return (GpioPinPwmOutput)provisionPin(provider, pin, name, PinMode.PWM_OUTPUT);
  }
  
  public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin)
  {
    return (GpioPinPwmOutput)provisionPin(provider, pin, PinMode.PWM_OUTPUT);
  }
  
  public GpioPinPwmOutput provisionPwmOutputPin(Pin pin, String name)
  {
    return provisionPwmOutputPin(this.defaultProvider, pin, name);
  }
  
  public GpioPinPwmOutput provisionPwmOutputPin(Pin pin)
  {
    return provisionPwmOutputPin(this.defaultProvider, pin);
  }
  
  public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin, int defaultValue)
  {
    return provisionPwmOutputPin(provider, pin, pin.getName(), defaultValue);
  }
  
  public GpioPinPwmOutput provisionPwmOutputPin(GpioProvider provider, Pin pin, String name, int defaultValue)
  {
    GpioPinPwmOutput gpioPin = provisionPwmOutputPin(provider, pin, name);
    

    gpioPin.setPwm(defaultValue);
    

    return gpioPin;
  }
  
  public GpioPinPwmOutput provisionPwmOutputPin(Pin pin, String name, int defaultValue)
  {
    return provisionPwmOutputPin(this.defaultProvider, pin, name, defaultValue);
  }
  
  public GpioPinPwmOutput provisionPwmOutputPin(Pin pin, int defaultValue)
  {
    return provisionPwmOutputPin(this.defaultProvider, pin, defaultValue);
  }
  
  public void unprovisionPin(GpioPin... pin)
  {
    if ((pin == null) || (pin.length == 0)) {
      throw new IllegalArgumentException("Missing pin argument.");
    }
    for (int index = pin.length - 1; index >= 0; index--)
    {
      GpioPin p = pin[index];
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      if ((p instanceof GpioPinInput))
      {
        ((GpioPinInput)p).removeAllListeners();
        ((GpioPinInput)p).removeAllTriggers();
      }
      this.pins.remove(p);
    }
  }
  
  public void setShutdownOptions(GpioPinShutdown options, GpioPin... pin)
  {
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.setShutdownOptions(options);
    }
  }
  
  public void setShutdownOptions(Boolean unexport, GpioPin... pin)
  {
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.setShutdownOptions(unexport);
    }
  }
  
  public void setShutdownOptions(Boolean unexport, PinState state, GpioPin... pin)
  {
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.setShutdownOptions(unexport, state);
    }
  }
  
  public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance, GpioPin... pin)
  {
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.setShutdownOptions(unexport, state, resistance);
    }
  }
  
  public void setShutdownOptions(Boolean unexport, PinState state, PinPullResistance resistance, PinMode mode, GpioPin... pin)
  {
    for (GpioPin p : pin)
    {
      if (!this.pins.contains(p)) {
        throw new GpioPinNotProvisionedException(p.getPin());
      }
      p.setShutdownOptions(unexport, state, resistance, mode);
    }
  }
  
  private class ShutdownHook
    extends Thread
  {
    private ShutdownHook() {}
    
    public void run()
    {
      GpioControllerImpl.this.shutdown();
    }
  }
  
  public boolean isShutdown()
  {
    return this.isshutdown;
  }
  
  public synchronized void shutdown()
  {
    if (isShutdown()) {
      return;
    }
    GpioFactory.getExecutorServiceFactory().shutdown();
    for (GpioPin pin : this.pins)
    {
      if (!pin.getProvider().isShutdown()) {
        pin.getProvider().shutdown();
      }
      GpioPinShutdown shutdownOptions = pin.getShutdownOptions();
      if (shutdownOptions != null)
      {
        PinState state = shutdownOptions.getState();
        PinMode mode = shutdownOptions.getMode();
        PinPullResistance resistance = shutdownOptions.getPullResistor();
        Boolean unexport = shutdownOptions.getUnexport();
        if ((state != null) && ((pin instanceof GpioPinDigitalOutput))) {
          ((GpioPinDigitalOutput)pin).setState(state);
        }
        if (resistance != null) {
          pin.setPullResistance(resistance);
        }
        if (mode != null) {
          pin.setMode(mode);
        }
        if ((unexport != null) && (unexport == Boolean.TRUE)) {
          pin.unexport();
        }
      }
    }
    this.isshutdown = true;
  }
}
