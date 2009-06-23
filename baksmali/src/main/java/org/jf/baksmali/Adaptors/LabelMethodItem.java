/*
 * [The "BSD licence"]
 * Copyright (c) 2009 Ben Gruver
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jf.baksmali.Adaptors;

public class LabelMethodItem extends MethodItem {
    private String labelPrefix;

    public LabelMethodItem(int offset, String labelPrefix) {
        super(offset);
        this.labelPrefix = labelPrefix;
    }

    public int getSortOrder() {
        return 0;
    }

    public int compareTo(MethodItem methodItem) {
        int result = super.compareTo(methodItem);

        if (result == 0) {
            if (methodItem instanceof LabelMethodItem) {
                result = labelPrefix.compareTo(((LabelMethodItem)methodItem).labelPrefix);
            }
        }
        return result;
    }

    public String getPrefix() {
        return labelPrefix;
    }

    public String getTemplate() {
        return "Label";
    }

    public int hashCode() {
        //force it to call equals when two labels are at the same address
        return getOffset();
    }

    public boolean equals(Object o) {
        if (!(o instanceof LabelMethodItem)) {
            return false;
        }
        return this.compareTo((MethodItem)o) == 0;
    }
}
