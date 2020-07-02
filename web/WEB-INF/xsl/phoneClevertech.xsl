<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:mobile="https://github.com/tranlinh281/schema/mobilephones"
                xmlns="https://github.com/tranlinh281/schema/mobilephones">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="mobile:Phones">
        <xsl:variable name="listDoc" select="document(@clevertech_link)" />
        <xsl:element name="Phones" xmlns="https://github.com/tranlinh281/schema/mobilephones">
            <xsl:for-each select="$listDoc//div[@class='col-products']/ul">
                <xsl:element name="Phone" xmlns="https://github.com/tranlinh281/schema/mobilephone">
                    <xsl:element name="LinkDetail">
                        <xsl:value-of select="li//div[@class='info']/h2/text()"/>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>

    </xsl:template>

</xsl:stylesheet>

<!--<xsl:element name="Phones" xmlns="https://github.com/tranlinh281/schema/mobilephones">
    <xsl:for-each select="$listDoc//div[@class='col-products']/ul">
        <xsl:element name="Phone" xmlns="https://github.com/tranlinh281/schema/mobilephone">
            <xsl:element name="LinkDetail">
                <xsl:value-of select="li//div[@class='info']/h2/text()"/>
            </xsl:element>
        </xsl:element>
    </xsl:for-each>
</xsl:element>-->
