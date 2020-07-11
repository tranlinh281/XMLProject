<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:shoes="https://github.com/tranlinh281/schema/listShoes"
                xmlns:dt="https://github.com/tranlinh281/schema/detail"
                xmlns="https://github.com/tranlinh281/schema/listShoes">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="shoes:ListShoes">
        <xsl:variable name="listDoc" select="document(@timan_link)" />
        <xsl:element name="ListShoes" xmlns="https://github.com/tranlinh281/schema/listShoes">
            <xsl:for-each select="$listDoc//dt:div[@class='product-grid']/dt:ul/dt:li/dt:h2/dt:a">
                <xsl:variable name="sName" select="/text()" />
                <xsl:element name="Shoes" xmlns="https://github.com/tranlinh281/schema/shoes">
                    <xsl:attribute name="LinkDetail">
                        <xsl:value-of select="@href"/>
                    </xsl:attribute>
                    <xsl:attribute name="Name">
                        <xsl:value-of select="$sName"/>
                    </xsl:attribute>
                    <xsl:variable name="detailShoes" />
                    <xsl:apply-templates >
                        <xsl:with-param name="GT"/>
                    </xsl:apply-templates>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="dt:div[@class='details']" name="GT">
        <shoes>
            <xsl:variable name="Gender" select="//dt:div[@class='product']//dt:h1"/>
            <Gender>
                <xsl:choose>
                    <xsl:when test="$Gender[contains(text(),'nữ')]">Nữ</xsl:when>
                    <xsl:when test="$Gender[contains(text(),'nam')]">Nam</xsl:when>
                    <xsl:otherwise>Unisex</xsl:otherwise>
                </xsl:choose>
            </Gender>
            <xsl:variable name="Category" select="//dt:div[@class='product']//dt:h1"/>            
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
            <xsl:variable name="Name" select="//dt:div[@class='product']//dt:h1/text()"/>
            <Name>
                <xsl:value-of select="$Name"/>
            </Name>
            <Description>
                <xsl:variable name="d_xuatxu" select=".//dt:div[@class='std']/dt:ul/dt:li[1]/dt:a/text()" />
                <xsl:variable name="d_chatlieu" select=".//dt:div[@class='std']/dt:ul/dt:li[2]/text()" />
                <xsl:variable name="d_kieudang" select=".//dt:div[@class='std']/dt:ul/dt:li[3]/dt:a/text()" />
                <xsl:variable name="d_mausac" select=".//dt:div[@class='std']/dt:ul/dt:li[4]/text()" />
                <xsl:variable name="d_diemkhacbiet" select=".//dt:div[@class='std']/dt:p[4]/dt:span/text()" />
                <xsl:variable name="d_mix" select=".//dt:div[@class='std']/dt:p[6]/dt:a/text()" />
            
                <xsl:call-template name="detail_des">
                    <xsl:with-param name="dName" select="'Xuất xứ'"/>
                    <xsl:with-param name="dvl" select="$d_xuatxu"/>
                </xsl:call-template>
                <xsl:call-template name="detail_des">
                    <xsl:with-param name="dName" select="'Chất liệu'"/>
                    <xsl:with-param name="dvl" select="$d_chatlieu"/>
                </xsl:call-template>
                <xsl:call-template name="detail_des">
                    <xsl:with-param name="dName" select="'Kiểu dáng'"/>
                    <xsl:with-param name="dvl" select="$d_kieudang"/>
                </xsl:call-template>
                <xsl:call-template name="detail_des">
                    <xsl:with-param name="dName" select="'Màu sắc'"/>
                    <xsl:with-param name="dvl" select="$d_mausac"/>
                </xsl:call-template>
                <xsl:call-template name="detail_des">
                    <xsl:with-param name="dName" select="'Đặc điểm'"/>
                    <xsl:with-param name="dvl" select="d_diemkhacbiet"/>
                </xsl:call-template>
                <xsl:call-template name="detail_des">
                    <xsl:with-param name="dName" select="'Mix đồ'"/>
                    <xsl:with-param name="dvl" select="d_mix"/>
                </xsl:call-template>
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

