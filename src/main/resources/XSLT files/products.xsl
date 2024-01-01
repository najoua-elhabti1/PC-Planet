<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0"
    xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="simple" page-height="11in" page-width="8.5in"
                    margin-top="1cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
                    <fo:region-body margin-top="2cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simple">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="12pt" font-family="serif" margin-top="5mm">
                        <!-- Ici, vous devez décrire comment extraire et formater les données XML -->
                        <xsl:apply-templates select="//product"/>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
    <xsl:template match="product">
        <!-- Vous devez décrire comment formater chaque élément product ici -->
        <fo:block>
            <fo:inline font-weight="bold">Product Name:</fo:inline> <xsl:value-of select="product_name"/><br/>
            <fo:inline font-weight="bold">Product Quantity:</fo:inline> <xsl:value-of select="qte_stock"/><br/>
            <fo:inline font-weight="bold">Product Description:</fo:inline> <xsl:value-of select="pr_description"/><br/>
            <!-- Ajoutez d'autres détails du produit au besoin -->
            <fo:block/><br/>
        </fo:block>
    </xsl:template>
    
</xsl:stylesheet>