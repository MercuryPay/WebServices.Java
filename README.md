MercuryWebServicesSDK
=====================

This is a rudimentary integration with hard-coded soap-envelope strings it is intended as a jumpstart not as a full integration example.

This repository includes:

* MercuryWebServiceJavaSDK -- a Java library that will make the necessary calls to Mercury's webservices.
* MercuryWebServiceJavaIntegrationExample -- a sample integration utilizing the above library

##Step 1: Create the Soap Envelope

In this example the soap envelope is hard coded

```
private final String mCreditTran = "<TStream>\n\t<Transaction>\n\t\t<MerchantID>118725340908147</MerchantID>\n\t\t<LaneID>02</LaneID>\n\t\t<TranType>Credit</TranType>\n\t\t<TranCode>Sale</TranCode>\n\t\t<InvoiceNo>1</InvoiceNo>\n\t\t<RefNo>1</RefNo>\n\t\t<Memo>MPS Example XML v1.0 - Java SDK</Memo>\n\t\t<PartialAuth>Allow</PartialAuth>\n\t\t<Frequency>OneTime</Frequency>\n\t\t<RecordNo>RecordNumberRequested</RecordNo>\n\t\t<Account>\n\t\t\t<EncryptedFormat>MagneSafe</EncryptedFormat>\n\t\t\t<AccountSource>Swiped</AccountSource>\n\t\t\t<EncryptedBlock>F40DDBA1F645CC8DB85A6459D45AFF8002C244A0F74402B479ABC9915EC9567C81BE99CE4483AF3D</EncryptedBlock>\n\t\t\t<EncryptedKey>9012090B01C4F200002B</EncryptedKey>\n\t\t\t<Name>MPS TEST</Name>\n\t\t</Account>\n\t\t<Amount>\n\t\t\t<Purchase>1.00</Purchase>\n\t\t</Amount>\n\t\t<TerminalName>MPS Java SDK</TerminalName>\n\t\t<ShiftID>MPS Shift</ShiftID>\n\t\t<OperatorID>MPS Operator</OperatorID>\n\t</Transaction>\n</TStream>",

```

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

[![Analytics](https://ga-beacon.appspot.com/UA-1785046-15/WebServices.Java/readme?pixel)](https://github.com/MercuryPay)

