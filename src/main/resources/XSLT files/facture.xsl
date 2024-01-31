<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                exclude-result-prefixes="xs"
                version="2.0"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <!-- Modèle pour l'élément 'cart' -->
    <xsl:template match="cart">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="page" page-width="8.5in" page-height="11in">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="16pt" text-align="center">Facture</fo:block>
                    <xsl:apply-templates select="user"/>
                    <xsl:apply-templates select="products"/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <!-- Modèle pour l'élément 'user' -->
    <xsl:template match="user">
        <fo:block>
            <fo:block font-weight="bold">User Information</fo:block>
            <fo:block>User ID: <xsl:value-of select="user_id"/></fo:block>
            <fo:block>Username: <xsl:value-of select="username"/></fo:block>
        </fo:block>
    </xsl:template>

    <!-- Modèle pour l'élément 'products' -->
    <xsl:template match="products">
        <fo:block>
            <fo:block font-weight="bold">Products</fo:block>
            <fo:table>
                <fo:table-column column-number="1" column-width="1in"/>
                <fo:table-column column-number="2" column-width="2in"/>
                <fo:table-column column-number="3" column-width="1in"/>
                <fo:table-header>
                    <fo:table-row>
                        <fo:table-cell><fo:block>Product ID</fo:block></fo:table-cell>
                        <fo:table-cell><fo:block>Product Name</fo:block></fo:table-cell>
                        <fo:table-cell><fo:block>Price</fo:block></fo:table-cell>
                    </fo:table-row>
                </fo:table-header>
                <fo:table-body>
                    <xsl:apply-templates select="product"/>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>

    <!-- Modèle pour l'élément 'product' -->
    <xsl:template match="product">
        <fo:table-row>
            <fo:table-cell><fo:block><xsl:value-of select="product_id"/></fo:block></fo:table-cell>
            <fo:table-cell><fo:block><xsl:value-of select="product_name"/></fo:block></fo:table-cell>
            <fo:table-cell><fo:block><xsl:value-of select="price"/></fo:block></fo:table-cell>
        </fo:table-row>
    </xsl:template>

</xsl:stylesheet>
