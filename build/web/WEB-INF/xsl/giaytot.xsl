<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : timan_detail_product.xsl
    Created on : July 2, 2020, 1:41 PM
    Author     : TranLinh
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:shoes="https://github.com/tranlinh281/schema/listShoes"
                xmlns="https://github.com/tranlinh281/schema/listShoes">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="shoes:ListShoes">
        <xsl:variable name="listDoc" select="document(@giaytot_link)" />
        <xsl:element name="ListShoes" xmlns="https://github.com/tranlinh281/schema/listShoes">
            <xsl:for-each select="$listDoc//div[@class='product-grid']/ul">
                <xsl:element name="Shoes" xmlns="https://github.com/tranlinh281/schema/shoes">
                    <xsl:element name="LinkDetail">
                        <xsl:value-of select="li//a/@href"/>
                    </xsl:element>
                    <xsl:variable name="Gender" select="//h3/a"/>
                    <gender>
                        <xsl:choose>
                            <xsl:when test="$Gender[contains(text(),'nữ')]">Nữ</xsl:when>
                            <xsl:when test="$Gender[contains(text(),'nam')]">Nam</xsl:when>
                            <xsl:otherwise>Unisex</xsl:otherwise>
                        </xsl:choose>
                    </gender>
<!--                    <xsd:element name="Category" type="xsd:string"/>
                    <xsd:element name="Name" type="xsd:string"/>
                    <xsd:element name="Description" type="xsd:string"/>
                    <xsd:element name="Price" type="xsd:positiveInteger"/>
                    <xsd:element name="ImgSrc" type="xsd:string"/>-->
                </xsl:element>
            </xsl:for-each>
        </xsl:element>

    </xsl:template>

</xsl:stylesheet>
