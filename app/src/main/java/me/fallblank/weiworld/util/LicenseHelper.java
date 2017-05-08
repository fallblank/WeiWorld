package me.fallblank.weiworld.util;

import java.util.ArrayList;
import java.util.List;

import me.fallblank.weiworld.bean.SourceBean;

/**
 * Created by fallb on 2017/5/8.
 */

public class LicenseHelper {
    private static List<SourceBean> sList;
    
    public static List<SourceBean> initLicense() {
        if (sList != null) {
            return sList;
        }
        
        sList = new ArrayList<>();
        
        SourceBean bean = new SourceBean();
        bean.setName("Butter Knife");
        bean.setAuthor("JakeWharton");
        bean.setUrl("https://github.com/JakeWharton/butterknife");
        bean.setLicense("Copyright 2013 Jake Wharton\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.");
        sList.add(bean);
        
        bean = new SourceBean();
        bean.setName("Retrofit");
        bean.setAuthor("square");
        bean.setUrl("https://github.com/square/retrofit");
        bean.setLicense("Copyright 2013 Square, Inc.\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.");
        sList.add(bean);
        
        bean = new SourceBean();
        bean.setName("RxJava");
        bean.setAuthor("ReactiveX");
        bean.setUrl("https://github.com/ReactiveX/RxJava");
        bean.setLicense("Copyright (c) 2016-present, RxJava Contributors.\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at\n" +
                "\n" +
                "http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writi" +
                "ng, software distributed under the License is distributed on an \"AS IS\" BASI" +
                "S, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the Licens" +
                "e for the specific language governing permissions and limitations under the License.");
        
        sList.add(bean);
        
        bean = new SourceBean();
        bean.setName("RxAndroid");
        bean.setAuthor("ReactiveX");
        bean.setUrl("https://github.com/ReactiveX/RxAndroid");
        bean.setLicense("Copyright 2015 The RxAndroid authors\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "    http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.");
        sList.add(bean);
        
        bean = new SourceBean();
        bean.setName("google-gson");
        bean.setAuthor("google");
        bean.setUrl("https://github.com/google/gson");
        bean.setLicense("Copyright 2008 Google Inc.\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "    http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.");
        sList.add(bean);
        
        bean = new SourceBean();
        bean.setName("Gson Converter");
        bean.setAuthor("square");
        bean.setUrl("https://github.com/square/retrofit/tree/master/retrofit-converters/gson");
        bean.setLicense("Copyright 2013 Square, Inc.\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.");
        sList.add(bean);
        
        bean = new SourceBean();
        bean.setName("RxJava2 Adapter");
        bean.setAuthor("square");
        bean.setUrl("https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2");
        bean.setLicense("Copyright 2013 Square, Inc.\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.");
        sList.add(bean);
        
        bean = new SourceBean();
        bean.setName("Fresco");
        bean.setAuthor("facebook");
        bean.setUrl("https://github.com/facebook/fresco");
        bean.setLicense("Fresco is BSD-licensed. We also provide an additional patent grant.");
        sList.add(bean);
        
        bean = new SourceBean();
        bean.setName("PhotoView");
        bean.setAuthor("chrisbanes");
        bean.setUrl("https://github.com/chrisbanes/PhotoView");
        bean.setLicense("Copyright 2017 Chris Banes\n" +
                "\n" +
                "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "you may not use this file except in compliance with the License.\n" +
                "You may obtain a copy of the License at\n" +
                "\n" +
                "   http://www.apache.org/licenses/LICENSE-2.0\n" +
                "\n" +
                "Unless required by applicable law or agreed to in writing, software\n" +
                "distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "See the License for the specific language governing permissions and\n" +
                "limitations under the License.");
        sList.add(bean);
        
        return sList;
    }
}
