<!--<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:shoes="https://github.com/tranlinh281/schema/listShoes"
                xmlns:dt="https://github.com/tranlinh281/schema/detail"
                xmlns="https://github.com/tranlinh281/schema/listShoes">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="shoes:ListShoes">
        <xsl:variable name="listDoc" select="document(@giaytot_link)" />
        <xsl:element name="ListShoes" xmlns="https://github.com/tranlinh281/schema/listShoes">
            <xsl:for-each select="$listDoc//dt:div[contains(@class, 'product-small col has-hover product type-product')]//dt:p[@class='name product-title']/dt:h3/dt:a">
                <xsl:variable name="sName" select="/text()" />
                <xsl:element name="Shoes" xmlns="https://github.com/tranlinh281/schema/shoes">
                    <xsl:attribute name="LinkDetail">
                        <xsl:value-of select="@href"/>
                    </xsl:attribute>
                    <xsl:attribute name="Name">
                        <xsl:value-of select="$sName"/>
                    </xsl:attribute>
                    <xsl:variable name="detailShoes" />
                    <xsl:apply-templates name="TiMan"/>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="dt:div[@class='product-main']" name="TiMan">
        <shoes>
            <xsl:variable name="Gender" select="//dt:div[contains(@class, 'product-info')]//dt:h1[@class='product-title entry-title']"/>
            <Gender>
                <xsl:choose>
                    <xsl:when test="$Gender[contains(text(),'nữ')]">Nữ</xsl:when>
                    <xsl:when test="$Gender[contains(text(),'nam')]">Nam</xsl:when>
                    <xsl:otherwise>Unisex</xsl:otherwise>
                </xsl:choose>
            </Gender>
            <xsl:variable name="Category" select="//dt:div[contains(@class, 'product-info')]//dt:h1[@class='product-title entry-title']"/>            
            <Category>
                <xsl:choose>
                    <xsl:when test="$Category[contains(text(),'nữ')]">
                        <xsl:value-of select="substring-before($Category,' nữ')"/>
                    </xsl:when>
                    <xsl:when test="$Category[contains(text(),'nam')]">
                        <xsl:value-of select="substring-before($Category,' nam')"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="$Category"/>
                    </xsl:otherwise>
                </xsl:choose>
            </Category>
            <xsl:variable name="Name" select="//dt:div[contains(@class, 'product-info')]//dt:h1[@class='product-title entry-title']/text()"/>
            <Name>
                <xsl:value-of select="$Name"/>
            </Name>
            <Description>
                <xsl:value-of select="//div[@class='product-short-description']/p/text()"
                
            </Description>
            
            <xsl:variable name="Price" select="//span[@class='price']/text()"/>
            <Price>
                <xsl:value-of select="translate($Price,'.','')"/>
            </Price>
            <ImgSrc>
                <xsl:value-of select="//div[@class='item']//img/@src"/>
            </ImgSrc>
        </shoes>
    </xsl:template>
        
    <xsl:template name="detail_des">
        <xsl:param name="dName"/>
        <xsl:param name="dvl"/>
    </xsl:template>
    

</xsl:stylesheet>
-->
