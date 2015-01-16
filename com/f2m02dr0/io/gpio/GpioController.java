package com.f2m02dr0.io.gpio;

import com.f2m02dr0.io.gpio.event.GpioPinListener;
import com.f2m02dr0.io.gpio.trigger.GpioTrigger;
import java.util.Collection;

public abstract interface GpioController
{
  public abstract void export(PinMode paramPinMode, GpioPin... paramVarArgs);
  
  public abstract boolean isExported(GpioPin... paramVarArgs);
  
  public abstract void unexport(GpioPin... paramVarArgs);
  
  public abstract void unexportAll();
  
  public abstract void setMode(PinMode paramPinMode, GpioPin... paramVarArgs);
  
  public abstract PinMode getMode(GpioPin paramGpioPin);
  
  public abstract boolean isMode(PinMode paramPinMode, GpioPin... paramVarArgs);
  
  public abstract void setPullResistance(PinPullResistance paramPinPullResistance, GpioPin... paramVarArgs);
  
  public abstract PinPullResistance getPullResistance(GpioPin paramGpioPin);
  
  public abstract boolean isPullResistance(PinPullResistance paramPinPullResistance, GpioPin... paramVarArgs);
  
  public abstract void high(GpioPinDigitalOutput... paramVarArgs);
  
  public abstract boolean isHigh(GpioPinDigital... paramVarArgs);
  
  public abstract void low(GpioPinDigitalOutput... paramVarArgs);
  
  public abstract boolean isLow(GpioPinDigital... paramVarArgs);
  
  public abstract void setState(PinState paramPinState, GpioPinDigitalOutput... paramVarArgs);
  
  public abstract void setState(boolean paramBoolean, GpioPinDigitalOutput... paramVarArgs);
  
  public abstract boolean isState(PinState paramPinState, GpioPinDigital... paramVarArgs);
  
  public abstract PinState getState(GpioPinDigital paramGpioPinDigital);
  
  public abstract void toggle(GpioPinDigitalOutput... paramVarArgs);
  
  public abstract void pulse(long paramLong, GpioPinDigitalOutput... paramVarArgs);
  
  public abstract void setValue(double paramDouble, GpioPinAnalogOutput... paramVarArgs);
  
  public abstract double getValue(GpioPinAnalog paramGpioPinAnalog);
  
  public abstract void addListener(GpioPinListener paramGpioPinListener, GpioPinInput... paramVarArgs);
  
  public abstract void addListener(GpioPinListener[] paramArrayOfGpioPinListener, GpioPinInput... paramVarArgs);
  
  public abstract void removeListener(GpioPinListener paramGpioPinListener, GpioPinInput... paramVarArgs);
  
  public abstract void removeListener(GpioPinListener[] paramArrayOfGpioPinListener, GpioPinInput... paramVarArgs);
  
  public abstract void removeAllListeners();
  
  public abstract void addTrigger(GpioTrigger paramGpioTrigger, GpioPinInput... paramVarArgs);
  
  public abstract void addTrigger(GpioTrigger[] paramArrayOfGpioTrigger, GpioPinInput... paramVarArgs);
  
  public abstract void removeTrigger(GpioTrigger paramGpioTrigger, GpioPinInput... paramVarArgs);
  
  public abstract void removeTrigger(GpioTrigger[] paramArrayOfGpioTrigger, GpioPinInput... paramVarArgs);
  
  public abstract void removeAllTriggers();
  
  public abstract GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider paramGpioProvider, Pin paramPin, String paramString, PinMode paramPinMode, PinPullResistance paramPinPullResistance);
  
  public abstract GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider paramGpioProvider, Pin paramPin, PinMode paramPinMode, PinPullResistance paramPinPullResistance);
  
  public abstract GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider paramGpioProvider, Pin paramPin, String paramString, PinMode paramPinMode);
  
  public abstract GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(GpioProvider paramGpioProvider, Pin paramPin, PinMode paramPinMode);
  
  public abstract GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin paramPin, String paramString, PinMode paramPinMode, PinPullResistance paramPinPullResistance);
  
  public abstract GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin paramPin, PinMode paramPinMode, PinPullResistance paramPinPullResistance);
  
  public abstract GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin paramPin, String paramString, PinMode paramPinMode);
  
  public abstract GpioPinDigitalMultipurpose provisionDigitalMultipurposePin(Pin paramPin, PinMode paramPinMode);
  
  public abstract GpioPinDigitalInput provisionDigitalInputPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString, PinPullResistance paramPinPullResistance);
  
  public abstract GpioPinDigitalInput provisionDigitalInputPin(GpioProvider paramGpioProvider, Pin paramPin, PinPullResistance paramPinPullResistance);
  
  public abstract GpioPinDigitalInput provisionDigitalInputPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString);
  
  public abstract GpioPinDigitalInput provisionDigitalInputPin(GpioProvider paramGpioProvider, Pin paramPin);
  
  public abstract GpioPinDigitalInput provisionDigitalInputPin(Pin paramPin, String paramString, PinPullResistance paramPinPullResistance);
  
  public abstract GpioPinDigitalInput provisionDigitalInputPin(Pin paramPin, PinPullResistance paramPinPullResistance);
  
  public abstract GpioPinDigitalInput provisionDigitalInputPin(Pin paramPin, String paramString);
  
  public abstract GpioPinDigitalInput provisionDigitalInputPin(Pin paramPin);
  
  public abstract GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString, PinState paramPinState);
  
  public abstract GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider paramGpioProvider, Pin paramPin, PinState paramPinState);
  
  public abstract GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString);
  
  public abstract GpioPinDigitalOutput provisionDigitalOutputPin(GpioProvider paramGpioProvider, Pin paramPin);
  
  public abstract GpioPinDigitalOutput provisionDigitalOutputPin(Pin paramPin, String paramString, PinState paramPinState);
  
  public abstract GpioPinDigitalOutput provisionDigitalOutputPin(Pin paramPin, PinState paramPinState);
  
  public abstract GpioPinDigitalOutput provisionDigitalOutputPin(Pin paramPin, String paramString);
  
  public abstract GpioPinDigitalOutput provisionDigitalOutputPin(Pin paramPin);
  
  public abstract GpioPinAnalogInput provisionAnalogInputPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString);
  
  public abstract GpioPinAnalogInput provisionAnalogInputPin(GpioProvider paramGpioProvider, Pin paramPin);
  
  public abstract GpioPinAnalogInput provisionAnalogInputPin(Pin paramPin, String paramString);
  
  public abstract GpioPinAnalogInput provisionAnalogInputPin(Pin paramPin);
  
  public abstract GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString, double paramDouble);
  
  public abstract GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider paramGpioProvider, Pin paramPin, double paramDouble);
  
  public abstract GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString);
  
  public abstract GpioPinAnalogOutput provisionAnalogOutputPin(GpioProvider paramGpioProvider, Pin paramPin);
  
  public abstract GpioPinAnalogOutput provisionAnalogOutputPin(Pin paramPin, String paramString, double paramDouble);
  
  public abstract GpioPinAnalogOutput provisionAnalogOutputPin(Pin paramPin, double paramDouble);
  
  public abstract GpioPinAnalogOutput provisionAnalogOutputPin(Pin paramPin, String paramString);
  
  public abstract GpioPinAnalogOutput provisionAnalogOutputPin(Pin paramPin);
  
  public abstract GpioPinPwmOutput provisionPwmOutputPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString, int paramInt);
  
  public abstract GpioPinPwmOutput provisionPwmOutputPin(GpioProvider paramGpioProvider, Pin paramPin, int paramInt);
  
  public abstract GpioPinPwmOutput provisionPwmOutputPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString);
  
  public abstract GpioPinPwmOutput provisionPwmOutputPin(GpioProvider paramGpioProvider, Pin paramPin);
  
  public abstract GpioPinPwmOutput provisionPwmOutputPin(Pin paramPin, String paramString, int paramInt);
  
  public abstract GpioPinPwmOutput provisionPwmOutputPin(Pin paramPin, int paramInt);
  
  public abstract GpioPinPwmOutput provisionPwmOutputPin(Pin paramPin, String paramString);
  
  public abstract GpioPinPwmOutput provisionPwmOutputPin(Pin paramPin);
  
  public abstract GpioPin provisionPin(GpioProvider paramGpioProvider, Pin paramPin, String paramString, PinMode paramPinMode);
  
  public abstract GpioPin provisionPin(GpioProvider paramGpioProvider, Pin paramPin, PinMode paramPinMode);
  
  public abstract GpioPin provisionPin(Pin paramPin, String paramString, PinMode paramPinMode);
  
  public abstract GpioPin provisionPin(Pin paramPin, PinMode paramPinMode);
  
  public abstract void setShutdownOptions(GpioPinShutdown paramGpioPinShutdown, GpioPin... paramVarArgs);
  
  public abstract void setShutdownOptions(Boolean paramBoolean, GpioPin... paramVarArgs);
  
  public abstract void setShutdownOptions(Boolean paramBoolean, PinState paramPinState, GpioPin... paramVarArgs);
  
  public abstract void setShutdownOptions(Boolean paramBoolean, PinState paramPinState, PinPullResistance paramPinPullResistance, GpioPin... paramVarArgs);
  
  public abstract void setShutdownOptions(Boolean paramBoolean, PinState paramPinState, PinPullResistance paramPinPullResistance, PinMode paramPinMode, GpioPin... paramVarArgs);
  
  public abstract Collection<GpioPin> getProvisionedPins();
  
  public abstract void unprovisionPin(GpioPin... paramVarArgs);
  
  public abstract boolean isShutdown();
  
  public abstract void shutdown();
}
