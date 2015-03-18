WebServices.Java
=====================

This is a rudimentary integration with hard-coded soap-envelope strings it is intended as a jumpstart not as a full integration example.

The repository has been validated with Eclipse Standard/SDK Luna Release 4.4.0

This repository includes:

* MercuryWebServiceJavaSDK -- a Java library that will make the necessary calls to Mercury's webservices.
* MercuryWebServiceJavaIntegrationExample -- a sample integration utilizing the above library

##Step 1: Create the Soap Envelope

In this example the transaction string is hard coded, you can find it at the top of MercuryWSExample.java.  There is a credit example and a prepaid example.  This value is then passed to the MercuryWebServicejavaSDK where it is formatted into a valid Soap Envelope using the buildSOAPRequest method.

##Step 2: Create a connection to webservices

```
HttpsURLConnection conn = (HttpsURLConnection)mWebServiceURL.openConnection();

conn.setDoOutput(true);
conn.setDoInput(true);
conn.setRequestMethod("POST");
conn.setReadTimeout(mTimeout);
conn.setConnectTimeout(mTimeout);
conn.setUseCaches(false);
conn.setDefaultUseCaches(false);
conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
conn.setRequestProperty("Content-Length", String.valueOf(soap.length()));
conn.setRequestProperty("SOAPAction", "\"" + mXMLNamespace + "/" + mWebMethodName + "\"");
conn.setRequestProperty("Connection", "Close");
```


##Step 3: Send Request

```
OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
wr.write(soap);
wr.flush();
wr.close();
```

##Step 4: Get Response

```
rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
String responseBuffer = "";
while ((responseBuffer = rd.readLine()) != null)
  responseData += responseBuffer;
```


###©2013 Mercury Payment Systems, LLC - all rights reserved.

Disclaimer:
This software and all specifications and documentation contained herein or provided to you hereunder (the "Software") are provided free of charge strictly on an "AS IS" basis. No representations or warranties are expressed or implied, including, but not limited to, warranties of suitability, quality, merchantability, or fitness for a particular purpose (irrespective of any course of dealing, custom or usage of trade), and all such warranties are expressly and specifically disclaimed. Mercury Payment Systems shall have no liability or responsibility to you nor any other person or entity with respect to any liability, loss, or damage, including lost profits whether foreseeable or not, or other obligation for any cause whatsoever, caused or alleged to be caused directly or indirectly by the Software. Use of the Software signifies agreement with this disclaimer notice.

![Analytics](https://ga-beacon.appspot.com/UA-60858025-25/WebServices.Java/readme?pixel)
