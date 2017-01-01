<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
<xsl:attribute-set name="osnovniPodatki">
	<xsl:attribute name="interniID"><xsl:value-of select="@id"/> </xsl:attribute>
	<xsl:attribute name="uporabniskoIme"><xsl:value-of select="substring(priimek, 0,3)"/><xsl:value-of select="ime"/> </xsl:attribute>
</xsl:attribute-set>

<xsl:template match="/">
	<xsl:element name="seznamKontaktov">
		<xsl:for-each select="imenik/oseba">
			<xsl:sort select="priimek"/>
			<xsl:element name="zaposleni" use-attribute-sets="osnovniPodatki">
				<xsl:element name="priimek"><xsl:value-of select="priimek"/>  </xsl:element>
				<xsl:element name="kontakt">
					<xsl:value-of select="telefon"/> 
				</xsl:element><xsl:comment>GSM: <xsl:value-of select="mobitel"/> </xsl:comment>
			</xsl:element>
		</xsl:for-each>
	</xsl:element>	
	
	
</xsl:template>	
	
</xsl:stylesheet>
