<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <!--<head runat="server32">-->
<head>
        <title>Reportviewer</title>
        <style type="text/css">
          .tableHeader
          {
          color: #FFFFFF;
          font-weight: bold;
          font-size: 15px;
          font-family: Verdana;
          background-color: #1B4F72;
          text-align: center;
          padding: 4px 6px;
          }
          .tableFooter
          {
          color: #AAAAAA;
          font-size: 13px;
          font-family: Verdana;
          text-align: center;
          padding: 3px 3px 1px;
          }
          .tableBorder
          {
          padding: 5px 5px 5px 5px;
          border-top: 3px solid #ccc;
          border-bottom: 3px solid #ccc;
          border-left: 3px solid #ccc;
          border-right: 3px solid #ccc;
          border-width: 2px;
          border-color: #D9D9FF;
          }
          .table_hl
          {
          margin: 2px;
          padding: 5px;
          color: #990000;
          font-weight: bold;
          font-size: 11px;
          font-family: Verdana;
          border-top: 1px solid #669;
          border-bottom: 1px solid #669;
          border: 1px solid #666699;
          text-align: center;
          }
          .table_cell
          {
          margin: 2px;
          padding: 5px;
          color: #000000;
          font-size: 11px;
          font-family: Verdana;
          border-top: 1px solid #669;
          border-bottom: 1px solid #669;
          border: 1px solid #666699;
          text-align: left;
          }
		  .table_cell_header{
		  margin: 2px;
          padding: 5px;
          color: #1B4F72;
          font-size: 12px;
          font-family: Verdana;
          border-top: 1px solid #669;
          border-bottom: 1px solid #669;
          border: 1px solid #666699;
          text-align: left;
		  background-color:#AED6F1;
		  font-weight: bold;
		  }
          .envDetCaption
          {
          padding: 2px;
          font-family: verdana;
          font-size: 11px;
          color: #000000;
          font-weight: bold;
          }
          .envDetValue
          {
          font-family: verdana;
          font-size: 11px;
          color: #000000;
          }
          .envDetColon
          {
          font-family: verdana;
          font-size: 11px;
          font-weight: bold;
          color: #000000;
          }
		  .mainHeader {
			/* background-color: #000000; */
			color: #000;
			font-weight: bold;
			font-size: 26px;
			border-bottom: 3px solid #000;
		}
        </style>
      </head>
      <body style="font-family: Verdana">
        <form id="form1" runat="server">
          <table width="100%">
            <tr>
              <td style="text-align: center" class="mainHeader">
                <span>
                  Reportviewer
                </span>
              </td>
            </tr>
			<tr>
				<td></td>
			</tr>
            <tr>
              <td>
                <table width="100%" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="tableHeader">
                      Environment Details
                    </td>
                  </tr>
                  <tr>
                    <td class="tableBorder">
                      <table width="98%" border="0" style="border-color: Black; overflow: visible" cellspacing="0"
                          cellpadding="0">
                        <tr>
                          <td class="envDetCaption" width="15%">
                            Project
                          </td>
                          <td class="envDetColon" width="5%">
                            :
                          </td>
                          <td class="envDetValue" colspan="4" style="color:orange">
                            <xsl:value-of select="TestCase/Details/ProjectName"/>
                          </td>
                        </tr>
                        <tr>
                          <td class="envDetCaption" width="15%">
                            Environment
                          </td>
                          <td class="envDetColon" width="5%">
                            :
                          </td>
                          <td class="envDetValue" width="40%">
                            <xsl:value-of select="TestCase/Details/Environment"/>
                          </td>
                          <td class="envDetCaption" width="10%">
                            User
                          </td>
                          <td class="envDetColon" width="5%">
                            :
                          </td>
                          <td class="envDetValue" width="25%">
                            <xsl:value-of select="TestCase/Details/User"/>
                          </td>
                        </tr>
                        <tr>
                          <td class="envDetCaption" width="15%">
                            TestCaseName
                          </td>
                          <td class="envDetColon" width="5%">
                            :
                          </td>
                          <td class="envDetValue" width="40%">
                            <xsl:value-of select="TestCase/Details/TestCaseName"/>
                          </td>
                          <td class="envDetCaption" width="10%">
                            Date
                          </td>
                          <td class="envDetColon" width="5%">
                            :
                          </td>
                          <td class="envDetValue" width="25%">
                            <xsl:value-of select="TestCase/Details/Date"/>
                          </td>
                        </tr>
                        <tr>
                          <td class="envDetCaption" width="15%">
                            RunId
                          </td>
                          <td class="envDetColon" width="5%">
                            :
                          </td>
                          <td class="envDetValue" width="40%">
                            <xsl:value-of select="TestCase/Details/RunId"/>
                          </td>
                          <td class="envDetCaption" width="10%">
                            Result
                          </td>
                          <td class="envDetColon" width="5%">
                            :
                          </td>
                          <xsl:choose>
                            <xsl:when test="TestCase/Details/Result = 'Pass'">
                              <td class="envDetValue" width="25%" style="color:Green; font-weight:bold">
                                Pass
                              </td>
                            </xsl:when>
                            <xsl:when test="TestCase/Details/Result = 'Fail'">
                              <td class="envDetValue" width="25%" style="color:Red; font-weight:bold">
                                Fail
                              </td>
                            </xsl:when>
                            <xsl:when test="TestCase/Details/Result = 'Warning'">
                              <td class="envDetValue" width="25%" style="color:Blue; font-weight:bold">
                                Warning
                              </td>
                            </xsl:when>
                            <xsl:otherwise>
                              <td class="envDetValue" width="25%" style="color:Black; font-weight:bold">
                                <xsl:value-of select="TestCase/Details/Result"/>
                              </td>
                            </xsl:otherwise>
                          </xsl:choose>
                        </tr>                        
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <xsl:if test="(count(TestCase/Steps/Step) > 0)">
            <tr>
              <td>-</td>
            </tr>
            <tr>
              <td>
                <table width="100%" cellspacing="0" cellpadding="0">
                  <tr>
                    <td>
                      <table width="100%" cellspacing="0" cellpadding="0">
                        <tr>
                          <td class="tableHeader">
                            Test Summary
                          </td>
                        </tr>
                        <tr>
                          <td class="tableBorder">
                            <table width="100%" border="0" style="border-color: Black; overflow: visible" cellspacing="0"
                                cellpadding="0">
                              <tr>
                                <td class="table_hl" width="2%">
                                  SNo.
                                </td>
                                <td class="table_hl" width="36%">
                                  Expected
                                </td>
                                <td class="table_hl" width="36%">
                                  Actual
                                </td>
                                <td class="table_hl" width="9%">
                                  Status
                                </td>
                                
                              </tr>
                            <xsl:for-each select="TestCase/Steps/Step">
							<xsl:if test="SubHeader">
								<tr>
									<td class="table_cell_header" colspan="5" style="text-align:center;">
										<xsl:value-of select="SubHeader"/>
									</td>
								</tr>
							</xsl:if>
							<xsl:if test="StepNumber">
							    <tr>
                                  <td class="table_cell" width="2%">
                                    <xsl:value-of select="StepNumber"/>.
                                  </td>
                                  <td class="table_cell" width="36%">
                                    <xsl:value-of select="Verification"/>
                                  </td>
                                  <td class="table_cell" width="36%">
							<xsl:value-of select="Description"/>				    
                                    <xsl:if test="Screenshot">	
				    <br/>
				    
					<!--<iframe width="100%" height="400" name="ErrorScreen" id="ErrorScreen" align="middle">-->
					<a target="_blank">
					<!--<img width="50%" height="400">-->
				    <xsl:attribute name="href">
				    <xsl:value-of select="Screenshot"/>
				    </xsl:attribute>
    				    <xsl:value-of select="Screenshot"/>
						</a>
				    <!--</img>-->
					<!--</iframe>-->
    				    </xsl:if>
				    </td>
				
                                  <xsl:choose>
                                    <xsl:when test="Status = 'Pass'">
                                      <td class="table_cell" width="12px" style="color: green; font-weight: bold; text-align:center;">
                                        Pass
                                      </td>
                                    </xsl:when>
                                    <xsl:when test="Status = 'Fail'">
                                      <td class="table_cell" width="12px" style="color: red; font-weight: bold; text-align:center;">
                                        Fail
                                      </td>
                                    </xsl:when>
                                    <xsl:when test="Status = 'Warning'">
                                      <td class="table_cell" width="12px" style="color: Blue; font-weight: bold; text-align:center;">
                                        Warning
                                      </td>
                                    </xsl:when>
                                    <xsl:otherwise>
                                      <td class="table_cell" width="12px" style="color: black; font-weight: bold; text-align:center;">
                                        <xsl:value-of select="Status"/>
                                      </td>
                                    </xsl:otherwise>
                                  </xsl:choose>
                                  <!--<td class="table_cell" width="17%">
                                    <xsl:value-of select="DateTime"/>
                                  </td>-->
                                </tr>
								</xsl:if>
                              </xsl:for-each>
                            </table>
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            </xsl:if>
            <tr>
              <td class="tableFooter">
                <br>
                  <br>
                    Generated by COE IP V1.0. Prolifics Corporation Proprietary.
                  </br>
                </br>
              </td>
            </tr>
          </table>
        </form>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>