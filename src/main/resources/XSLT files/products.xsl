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
                    <fo:region-body margin-top="0 cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simple">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="22pt" font-family="serif" margin-bottom="15mm" text-align="center" >
                        PcPlanet Catalogue 
                    </fo:block>
                    <xsl:apply-templates select="//product"/>
                </fo:flow>
              
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    
    <xsl:template match="product">
        
        <fo:block margin-bottom="10mm" >
            
            <fo:block font-size="14pt" font-weight="bold" text-align="left">
                <xsl:value-of select="product_name"/>
            </fo:block>
            
            
            <fo:block  text-align="left">
                <xsl:value-of select="pr_description"/>
            </fo:block>
            
            <fo:block text-align="left">
                <fo:inline font-weight="bold">Quantité:</fo:inline> <xsl:value-of select="qte_stock"/>
            </fo:block>
            <fo:block text-align="left">
                <fo:inline font-weight="bold">Price:</fo:inline> <xsl:value-of select="price"/>
            </fo:block>
            <fo:block text-align="center">
                <!-- Utilisez l'attribut 'image' pour référencer l'emplacement de l'image -->
                <fo:external-graphic src="url('{concat('src/main/resources/static/', '/', image)}')"  content-width="2in" content-type="image/png"/>
                
            </fo:block>
            <!-- Ajoutez d'autres détails du produit selon vos besoins -->
        </fo:block>
        
    </xsl:template>
    
    
   
    
</xsl:stylesheet>