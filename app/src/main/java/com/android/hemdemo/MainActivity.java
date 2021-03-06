/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.android.hemdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huawei.hem.license.HemLicenseManager;
import com.huawei.hem.license.HemLicenseStatusListener;

public class MainActivity extends Activity {
    private HemLicenseManager hemInstance;

    private TextView textResultCode;

    private TextView textResultCodeDesc;

    private Button buttonActive;

    private Button buttonDeActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hemInstance = HemLicenseManager.getInstance(this);
        setButtonClickListener();
        setStatusListener();

    }

    private void setButtonClickListener() {
        buttonActive = findViewById(R.id.active);
        buttonDeActive = findViewById(R.id.de_active);
        textResultCode = findViewById(R.id.result_code);
        textResultCodeDesc = findViewById(R.id.result_code_desc);
        buttonActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hemInstance.activeLicense();
            }
        });

        buttonDeActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hemInstance.deActiveLicense();
            }
        });
    }

    private void setStatusListener() {
        hemInstance.setStatusListener(new MyHemLicenseStatusListener());
    }

    private class MyHemLicenseStatusListener implements HemLicenseStatusListener {
        @Override
        public void onStatus(final int errorCode, final String msg) {
            textResultCode.post(new Runnable() {
                @Override
                public void run() {
                    textResultCode.setText(String.valueOf(errorCode));
                }
            });

            textResultCodeDesc.post(new Runnable() {
                @Override
                public void run() {
                    textResultCodeDesc.setText(msg);
                }
            });
        }
    }
}
