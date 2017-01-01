<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="imenik">
	<html>
		<head>
			<title>Seznam oseb</title>
		</head>
		<body>
			<h1>Imenik Univerze v Mariboru</h1>
			<img alt="UM FERI" src="UM.jpg"/>
			<hr/>
			<table border="1">
				<tbody>
					<tr>
						<th>ID</th>
						<th>Osebni podatki</th>
						<th>Kontakt</th>
					</tr>
					<xsl:apply-templates select="oseba"/>
				</tbody>
			</table>
		</body>
	</html>
</xsl:template>
<xsl:template match="oseba">
<tr>
	<td><i><xsl:apply-templates select="@id"/></i></td>
	<td><xsl:apply-templates  select="ime"/><xsl:apply-templates  select="priimek"/> </td>
	<td><xsl:apply-templates  select="telefon"/><xsl:apply-templates  select="mobitel"/></td>
</tr>
</xsl:template>
<xsl:template match="ime">
	<xsl:apply-templates/><xsl:text> </xsl:text>  
</xsl:template>
<xsl:template match="priimek">
	<xsl:apply-templates/>
</xsl:template>
<xsl:template match="telefon">
	<h5>TEL: <xsl:apply-templates/><br/></h5>
</xsl:template>
<xsl:template match="mobitel">
	<h5>Mobi: <xsl:apply-templates/><br/></h5>
</xsl:template>
</xsl:stylesheet>
