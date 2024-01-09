<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Book Information</h2>
                <a href="/book/books">All Books</a>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>UUID</th>
                        <th>Content</th>
                        <th>Is Deleted</th>
                        <th>Creation Date</th>
                        <th>Last Modified Date</th>
                    </tr>
                    <xsl:for-each select="Book">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="uuid"/></td>
                            <td><xsl:value-of select="content"/></td>
                            <td><xsl:value-of select="isDeleted"/></td>
                            <td><xsl:value-of select="creationDate"/></td>
                            <td><xsl:value-of select="lastModifiedDate"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>