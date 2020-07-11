<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:shoes="https://linhtt.org/schema/listShoes"
                xmlns:dt="https://linhtt.org/schema/detail"
                xmlns="https://linhtt.org/schema/listShoes">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>

    <xsl:template match="shoes:ListShoes">
        <xsl:variable name="listDoc" select="document(@hudo_nike)" />
        <xsl:element name="ListShoes" xmlns="https://linhtt.org/schema/listShoes">
            <xsl:for-each select="$listDoc//dt:div[@class='row filter-here']//dt:h3[@class='pro-name']/dt:a">
                <xsl:variable name="sName" select="/text()" />
                <xsl:element name="Shoes" xmlns="https://linhtt.org/schema/shoes">
                    <xsl:attribute name="linkProduct">
                        <xsl:value-of select="@href"/>
                    </xsl:attribute>
                    <xsl:attribute name="Name">
                        <xsl:value-of select="$sName"/>
                    </xsl:attribute>
                    <xsl:variable name="detailShoes" />
                    <xsl:apply-templates >
                        <xsl:with-param name="hodu"/>
                    </xsl:apply-templates>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
    
    <xsl:template name="hodu" match="dt:div[@class='row filter-here']">
        <xsl:variable name="gender" select="//dt:div[@class='product-title']/dt:h1" />
        <gender>
            <xsl:choose>
                <xsl:when test="$gender[contains(text(),'nam')]">Nam</xsl:when>
                <xsl:when test="$gender[contains(text(),'nữ')]">Nữ</xsl:when>
                <xsl:otherwise>Unisex</xsl:otherwise>
            </xsl:choose>
        </gender>
            
        <xsl:variable name="brand" select="//dt:div[@class='product-title']/dt:h1" />
        <brand>
            <xsl:choose>
                <xsl:when test="$brand[contains(text(),'Nike')]">
                    <xsl:value-of select="substring($brand, 6, 4)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'Adidas')]">
                    <xsl:value-of select="substring($brand, 6, 6)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'Gucci')]">
                    <xsl:value-of select="substring($brand, 6, 5)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'Puma')]">
                    <xsl:value-of select="substring($brand, 6, 4)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'Fila')]">
                    <xsl:value-of select="substring($brand, 6, 4)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'Converse')]">
                    <xsl:value-of select="substring($brand, 6, 8)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'Balenciaga')]">
                    <xsl:value-of select="substring($brand, 6, 10)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'New Balance')]">
                    <xsl:value-of select="substring($brand, 6, 11)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'Louis Vuitton')]">
                    <xsl:value-of select="substring($brand, 6, 12)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'Givenchy')]">
                    <xsl:value-of select="substring($brand, 6, 8)"  />
                </xsl:when>
                <xsl:when test="$brand[contains(text(),'Vans')]">
                    <xsl:value-of select="substring($brand, 6, 4)"  />
                </xsl:when>
                <xsl:otherwise>No Brand</xsl:otherwise>
            </xsl:choose>
        </brand>
        <xsl:variable name="name" select="//dt:div[@class='product-title']/dt:h1/text()" />
        <name>
            <xsl:value-of select="$name"/>
        </name>
        <xsl:variable name="des" select="//dt:div[@class='main_details des-content']"/>
        <description>
            <xsl:value-of select="$des" />
        </description>
            
        <xsl:variable name="price" select="//dt:span[@class='pro-price']/text()"/>
        <price>
            <xsl:value-of select="$price"/>
        </price>
            
        <xsl:variable name="imgSrc" select="//dt:img[@class='product-image-feature']/@src"/>
        <imgSrc>
            <xsl:value-of select="$imgSrc" />
        </imgSrc>
        <linkProduct>null</linkProduct>
    </xsl:template>
    

</xsl:stylesheet>
