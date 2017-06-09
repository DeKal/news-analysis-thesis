/*
* Static class: NamespaceManager
*/
// These three lines are used to register the namespace "oa.web.common"
if (typeof oa == 'undefined' || !oa) oa = {};
if (typeof oa.web == 'undefined' || !oa.web) oa.web = {};
if (typeof oa.web.utility == 'undefined' || !oa.web.utility) oa.web.utility = {};
oa.web.utility.NamespaceManager = {
    register: function (namespace) {
        namespace = namespace.split('.');

        if (!window[namespace[0]])
            window[namespace[0]] = {};

        var strFullNamespace = namespace[0];
        for (var i = 1; i < namespace.length; i++) {
            strFullNamespace += "." + namespace[i];
            eval("if(typeof window." + strFullNamespace + "== 'undefined' || !window." + strFullNamespace + ") window." + strFullNamespace + "={};");
        }
    }
};

/*
* Static class: ClassManager
* Description: help to do sub-classing
*/
oa.web.utility.ClassManager = {
    extend: function (childClass, parentClass) {
        var p = parentClass.prototype;
        var c = childClass.prototype;
        for (var i in p) {
            if (c.hasOwnProperty(i) == false) {
                if (typeof p[i] === 'object') {
                    c[i] = (p[i].constructor === Array) ? [] : {};
                    extend(p[i], c[i]);
                } else {
                    c[i] = p[i];
                }
            }
        }

        childClass.prototype.constructor = childClass;
        childClass.superClass = parentClass.prototype;

        if (parentClass.prototype.constructor == Object.prototype.constructor) {
            parentClass.prototype.constructor = parentClass;
        }
    }
};