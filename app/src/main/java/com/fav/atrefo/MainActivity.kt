package com.fav.atrefo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.fav.atrefo.ui.greeting.GreetingScreen
import com.fav.atrefo.ui.theme.MyApplicationTheme

// Ask AI if this is the best way to have the composable and the activity exist in such a way that
// it is testable 100% code and line coverage
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

// @Composable
// fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = greetingText("$name!"),
//        modifier = modifier,
//    )
// }

// In MainActivity.kt
// fun greetingText(name: String) = "Hello $name"
//
// @Preview(showBackground = true)
// @Composable
// fun GreetingPreview() {
//    // Previews should not be written in fragments...They should have the composable function and the
//    // preview in the same file. not in the fragment file
//    MyApplicationTheme {
//        Greeting("Android")
//    }
// }

/*
favourtraction@Favours-MacBook-Pro platform-tools % ./adb shell dumpsys connectivity
NetworkFactories for: TelephonyNetworkFactory[0] UntrustedWifiNetworkFactory Ethernet WifiNetworkFactory TelephonyNetworkFactory[1] PhoneSwitcherNetworkRequstListener

Active default network: 109

Current Networks:
NetworkAgentInfo{ ni{[type: MOBILE[LTE], state: CONNECTED/CONNECTED, reason: connected, extra: internet.ng.airtel.com, failover: false, available: true, roaming: false]}  network{109}  nethandle{471557132301}  lp{{InterfaceName: seth_lte0 LinkAddresses: [ 10.197.109.220/32 ] DnsAddresses: [ /172.24.250.231,/10.227.67.93 ] Domains: null MTU: 1500 IPv6MTU: 0 TcpBufferSizes: 524288,1048576,2097152,262144,524288,1048576 Routes: [ 0.0.0.0/0 -> 10.197.109.220 seth_lte0,10.197.109.220/32 -> 0.0.0.0 seth_lte0 ]}}  nc{[ Transports: CELLULAR Capabilities: SUPL&XCAP&INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&VALIDATED&NOT_ROAMING&FOREGROUND&NOT_CONGESTED&NOT_SUSPENDED LinkUpBandwidth>=51200Kbps LinkDnBandwidth>=102400Kbps Specifier: <2>]}  Score{50}  everValidated{true}  lastValidated{true}  created{true} lingering{false} explicitlySelected{false} acceptUnvalidated{false} everCaptivePortalDetected{false} lastCaptivePortalDetected{false} captivePortalValidationPending{false} partialConnectivity{false} acceptPartialConnectivity{false} clat{mBaseIface: null, mIface: null, mState: IDLE} }
Requests: REQUEST:2 LISTEN:8 BACKGROUND_REQUEST:1 total:11
NetworkRequest [ REQUEST id=1, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN] ]
NetworkRequest [ BACKGROUND_REQUEST id=2, [ Transports: CELLULAR Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN] ]
NetworkRequest [ LISTEN id=4, [ Capabilities: FOREGROUND] ]
NetworkRequest [ LISTEN id=5, [ Capabilities: NOT_RESTRICTED&TRUSTED&NOT_VPN&FOREGROUND Uid: 1000] ]
NetworkRequest [ LISTEN id=6, [ Capabilities: NOT_RESTRICTED&TRUSTED&NOT_VPN&FOREGROUND Uid: 1000] ]
NetworkRequest [ REQUEST id=7, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN] ]
NetworkRequest [ LISTEN id=8, [ Transports: CELLULAR Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&FOREGROUND Uid: 1000] ]
NetworkRequest [ TRACK_DEFAULT id=9, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 1000] ]
NetworkRequest [ TRACK_DEFAULT id=11, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10088] ]
NetworkRequest [ LISTEN id=12, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&VALIDATED Uid: 1000] ]
NetworkRequest [ LISTEN id=15, [] ]
Lingered:

Restrict background: false

Status for known UIDs:
UID=10041 rules=32 (ALLOW_ALL|NONE)
UID=10042 rules=64 (NONE|REJECT_ALL)
UID=10050 rules=64 (NONE|REJECT_ALL)
UID=10053 rules=64 (NONE|REJECT_ALL)
UID=10055 rules=64 (NONE|REJECT_ALL)
UID=10062 rules=64 (NONE|REJECT_ALL)
UID=10063 rules=64 (NONE|REJECT_ALL)
UID=10065 rules=64 (NONE|REJECT_ALL)
UID=10067 rules=64 (NONE|REJECT_ALL)
UID=10071 rules=64 (NONE|REJECT_ALL)
UID=10077 rules=32 (ALLOW_ALL|NONE)
UID=10078 rules=64 (NONE|REJECT_ALL)
UID=10079 rules=64 (NONE|REJECT_ALL)
UID=10080 rules=32 (ALLOW_ALL|NONE)
UID=10081 rules=64 (NONE|REJECT_ALL)
UID=10093 rules=64 (NONE|REJECT_ALL)
UID=10095 rules=64 (NONE|REJECT_ALL)
UID=10096 rules=64 (NONE|REJECT_ALL)
UID=10098 rules=64 (NONE|REJECT_ALL)
UID=10099 rules=64 (NONE|REJECT_ALL)
UID=10100 rules=64 (NONE|REJECT_ALL)
UID=10101 rules=64 (NONE|REJECT_ALL)
UID=10102 rules=64 (NONE|REJECT_ALL)
UID=10103 rules=64 (NONE|REJECT_ALL)
UID=10104 rules=64 (NONE|REJECT_ALL)
UID=10105 rules=64 (NONE|REJECT_ALL)
UID=10106 rules=64 (NONE|REJECT_ALL)
UID=10112 rules=64 (NONE|REJECT_ALL)
UID=10114 rules=64 (NONE|REJECT_ALL)
UID=10124 rules=64 (NONE|REJECT_ALL)

Network Requests:
uid/pid:1000/777 NetworkRequest [ REQUEST id=1, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN] ]
uid/pid:1000/777 NetworkRequest [ BACKGROUND_REQUEST id=2, [ Transports: CELLULAR Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN] ]
uid/pid:1000/777 NetworkRequest [ LISTEN id=4, [ Capabilities: FOREGROUND] ]
uid/pid:1000/777 NetworkRequest [ LISTEN id=5, [ Capabilities: NOT_RESTRICTED&TRUSTED&NOT_VPN&FOREGROUND Uid: 1000] ]
uid/pid:1000/777 NetworkRequest [ LISTEN id=6, [ Capabilities: NOT_RESTRICTED&TRUSTED&NOT_VPN&FOREGROUND Uid: 1000] ]
uid/pid:1000/777 NetworkRequest [ REQUEST id=7, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN] ]
uid/pid:1000/777 NetworkRequest [ LISTEN id=8, [ Transports: CELLULAR Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&NOT_VPN&FOREGROUND Uid: 1000] ]
uid/pid:1000/777 NetworkRequest [ TRACK_DEFAULT id=9, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 1000] ]
uid/pid:10088/1050 NetworkRequest [ LISTEN id=10, [ Transports: WIFI Capabilities: NOT_VPN] ]
uid/pid:10088/1050 NetworkRequest [ TRACK_DEFAULT id=11, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10088] ]
uid/pid:1000/777 NetworkRequest [ LISTEN id=12, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED&VALIDATED Uid: 1000] ]
uid/pid:1001/1011 NetworkRequest [ LISTEN id=13, [ Transports: WIFI Capabilities: INTERNET&TRUSTED&NOT_VPN Uid: 1001] ]
uid/pid:1001/1011 NetworkRequest [ LISTEN id=14, [ Transports: WIFI Capabilities: INTERNET&TRUSTED&NOT_VPN Uid: 1001] ]
uid/pid:10088/1050 NetworkRequest [ LISTEN id=15, [] ]

mLegacyTypeTracker:
Supported types: 0 1 2 3 4 5 9 10 11 12 13 14 15 17
Current state:
0 NetworkAgentInfo [MOBILE (LTE) - 109] CONNECTED/CONNECTED


Tethering:
Configuration:
subId: 2
tetherableUsbRegexs: [rndis0, usb0]
tetherableWifiRegexs: [wlan0]
tetherableBluetoothRegexs: [bt-pan]
isDunRequired: false
chooseUpstreamAutomatically: true
preferredUpstreamIfaceTypes: [ETHERNET, WIFI, BLUETOOTH, MOBILE]
legacyDhcpRanges: [192.168.42.2, 192.168.42.254, 192.168.43.2, 192.168.43.254, 192.168.44.2, 192.168.44.254, 192.168.45.2, 192.168.45.254, 192.168.46.2, 192.168.46.254, 192.168.47.2, 192.168.47.254, 192.168.48.2, 192.168.48.254, 192.168.49.2, 192.168.49.254, 192.168.137.2, 192.168.137.254, 192.168.0.2, 192.168.0.254]
defaultIPv4DNS: [8.8.4.4, 8.8.8.8]
provisioningApp: []
provisioningAppNoUi:
enableLegacyDhcpServer: false
Entitlement:
mCellularUpstreamPermitted: true
Tether state:
Upstream wanted: false
Current upstream interface(s): null
Hardware offload:
Offload disabled
Log:
2026-07-30T09:36:05.989 - MARK constructed
2026-07-30T09:36:05.994 - [config] TetheringConfiguration{subId:-1 tetherableUsbRegexs:[rndis0,usb0] tetherableWifiRegexs:[wlan0] tetherableBluetoothRegexs:[bt-pan] isDunRequired:false chooseUpstreamAutomatically:true preferredUpstreamIfaceTypes:[ETHERNET,WIFI,BLUETOOTH,MOBILE] provisioningApp:[] provisioningAppNoUi: enableLegacyDhcpServer:false}
2026-07-30T09:36:13.589 - OBSERVED carrier config change
2026-07-30T09:36:13.608 - [config] TetheringConfiguration{subId:2 tetherableUsbRegexs:[rndis0,usb0] tetherableWifiRegexs:[wlan0] tetherableBluetoothRegexs:[bt-pan] isDunRequired:false chooseUpstreamAutomatically:true preferredUpstreamIfaceTypes:[ETHERNET,WIFI,BLUETOOTH,MOBILE] provisioningApp:[] provisioningAppNoUi: enableLegacyDhcpServer:false}
2026-07-30T09:36:14.389 - OBSERVED default data subscription change
2026-07-30T09:36:14.514 - OBSERVED carrier config change
2026-07-30T09:36:14.538 - [config] TetheringConfiguration{subId:2 tetherableUsbRegexs:[rndis0,usb0] tetherableWifiRegexs:[wlan0] tetherableBluetoothRegexs:[bt-pan] isDunRequired:false chooseUpstreamAutomatically:true preferredUpstreamIfaceTypes:[ETHERNET,WIFI,BLUETOOTH,MOBILE] provisioningApp:[] provisioningAppNoUi: enableLegacyDhcpServer:false}
2026-07-30T09:36:17.971 - USB bcast connected:false configured:false rndis:false
2026-07-30T09:36:18.684 - OBSERVED configuration changed
2026-07-30T09:36:18.700 - [config] TetheringConfiguration{subId:2 tetherableUsbRegexs:[rndis0,usb0] tetherableWifiRegexs:[wlan0] tetherableBluetoothRegexs:[bt-pan] isDunRequired:false chooseUpstreamAutomatically:true preferredUpstreamIfaceTypes:[ETHERNET,WIFI,BLUETOOTH,MOBILE] provisioningApp:[] provisioningAppNoUi: enableLegacyDhcpServer:false}
2026-07-30T09:36:22.569 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T09:36:22.571 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T09:36:23.490 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T09:36:23.599 - OBSERVED carrier config change
2026-07-30T09:36:23.628 - [config] TetheringConfiguration{subId:2 tetherableUsbRegexs:[rndis0,usb0] tetherableWifiRegexs:[wlan0] tetherableBluetoothRegexs:[bt-pan] isDunRequired:false chooseUpstreamAutomatically:true preferredUpstreamIfaceTypes:[ETHERNET,WIFI,BLUETOOTH,MOBILE] provisioningApp:[] provisioningAppNoUi: enableLegacyDhcpServer:false}
2026-07-30T09:36:24.835 - OBSERVED carrier config change
2026-07-30T09:36:24.855 - [config] TetheringConfiguration{subId:2 tetherableUsbRegexs:[rndis0,usb0] tetherableWifiRegexs:[wlan0] tetherableBluetoothRegexs:[bt-pan] isDunRequired:false chooseUpstreamAutomatically:true preferredUpstreamIfaceTypes:[ETHERNET,WIFI,BLUETOOTH,MOBILE] provisioningApp:[] provisioningAppNoUi: enableLegacyDhcpServer:false}
2026-07-30T09:36:25.033 - OBSERVED carrier config change
2026-07-30T09:36:25.109 - [config] TetheringConfiguration{subId:2 tetherableUsbRegexs:[rndis0,usb0] tetherableWifiRegexs:[wlan0] tetherableBluetoothRegexs:[bt-pan] isDunRequired:false chooseUpstreamAutomatically:true preferredUpstreamIfaceTypes:[ETHERNET,WIFI,BLUETOOTH,MOBILE] provisioningApp:[] provisioningAppNoUi: enableLegacyDhcpServer:false}
2026-07-30T09:39:25.603 - USB bcast connected:true configured:false rndis:false
2026-07-30T09:39:25.604 - USB bcast connected:true configured:true rndis:false
2026-07-30T09:58:11.839 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T09:58:12.069 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T09:58:22.360 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T09:58:23.599 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T09:58:32.893 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T09:58:43.180 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T09:58:43.413 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:02:34.870 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:02:45.276 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T10:02:45.541 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:06:37.145 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:06:37.776 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T10:06:38.082 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:25:11.758 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:25:22.619 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T10:25:23.678 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:25:32.991 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:25:43.426 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T10:25:43.638 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:51:37.678 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T10:51:43.021 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T10:51:43.248 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T11:03:57.909 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T11:04:07.467 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T11:04:08.532 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T11:04:17.872 - InitialState got CMD_UPSTREAM_CHANGED
2026-07-30T11:04:28.306 - seth_lte0 is not a tetherable iface, ignoring
2026-07-30T11:04:28.500 - InitialState got CMD_UPSTREAM_CHANGED

Supported Socket keepalives: [1, 3, 0, 0, 0, 0, 0, 0]
Reserved Privileged keepalives: 2
Allowed Unprivileged keepalives per uid: 2
Socket keepalives:

Bad Wi-Fi avoidance: unrestricted

MultipathPolicyTracker:
Network 109: quota 3579137, budget 3577853. Preference: HANDOVER|RELIABILITY

mNetworkRequestInfoLogs (most recent first):
2026-07-30T10:44:58.355 - RELEASE uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=26, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:43:04.535 - REGISTER uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=26, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:42:31.649 - RELEASE uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=25, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:41:58.464 - REGISTER uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=25, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:41:54.013 - RELEASE uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=24, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:41:36.238 - REGISTER uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=24, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:41:07.055 - RELEASE uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=23, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:40:20.433 - REGISTER uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=23, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:38:13.192 - RELEASE uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=22, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:37:08.729 - REGISTER uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=22, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:29:07.883 - RELEASE uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=21, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:28:08.103 - REGISTER uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=21, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:25:11.772 - RELEASE uid/pid:1000/777 NetworkRequest [ REQUEST id=20, legacyType=3, [ Transports: CELLULAR Capabilities: SUPL&NOT_RESTRICTED&TRUSTED&NOT_VPN Uid: 1000] ]
2026-07-30T10:18:41.516 - REGISTER uid/pid:1000/777 NetworkRequest [ REQUEST id=20, legacyType=3, [ Transports: CELLULAR Capabilities: SUPL&NOT_RESTRICTED&TRUSTED&NOT_VPN Uid: 1000] ]
2026-07-30T10:04:23.161 - RELEASE uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=19, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T10:03:24.962 - REGISTER uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=19, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T09:55:19.297 - RELEASE uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=18, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T09:39:57.753 - REGISTER uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=18, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T09:38:46.061 - RELEASE uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=17, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]
2026-07-30T09:37:51.219 - REGISTER uid/pid:10124/2435 NetworkRequest [ TRACK_DEFAULT id=17, [ Capabilities: INTERNET&NOT_RESTRICTED&TRUSTED Uid: 10124] ]

mNetworkInfoBlockingLogs (most recent first):

NetTransition WakeLock activity (most recent first):
total acquisitions: 8
total releases: 8
cumulative duration: 79s
longest duration: 12s
2026-07-30T11:04:29.493 - RELEASE (EVENT_CLEAR_NET_TRANSITION_WAKELOCK)
2026-07-30T11:04:17.869 - ACQUIRE for NetworkAgentInfo [MOBILE (HSPA+) - 108]
2026-07-30T11:04:09.530 - RELEASE (EVENT_CLEAR_NET_TRANSITION_WAKELOCK)
2026-07-30T11:03:57.901 - ACQUIRE for NetworkAgentInfo [MOBILE (LTE) - 107]
2026-07-30T10:51:44.242 - RELEASE (EVENT_CLEAR_NET_TRANSITION_WAKELOCK)
2026-07-30T10:51:37.676 - ACQUIRE for NetworkAgentInfo [MOBILE (LTE) - 106]
2026-07-30T10:25:44.633 - RELEASE (EVENT_CLEAR_NET_TRANSITION_WAKELOCK)
2026-07-30T10:25:32.978 - ACQUIRE for NetworkAgentInfo [MOBILE (HSPA) - 105]
2026-07-30T10:06:39.074 - RELEASE (EVENT_CLEAR_NET_TRANSITION_WAKELOCK)
2026-07-30T10:06:37.138 - ACQUIRE for NetworkAgentInfo [MOBILE (LTE) - 103]
2026-07-30T10:02:46.532 - RELEASE (EVENT_CLEAR_NET_TRANSITION_WAKELOCK)
2026-07-30T10:02:34.866 - ACQUIRE for NetworkAgentInfo [MOBILE (LTE) - 102]
2026-07-30T09:58:44.401 - RELEASE (EVENT_CLEAR_NET_TRANSITION_WAKELOCK)
2026-07-30T09:58:32.891 - ACQUIRE for NetworkAgentInfo [MOBILE (LTE) - 101]
2026-07-30T09:58:24.592 - RELEASE (EVENT_CLEAR_NET_TRANSITION_WAKELOCK)
2026-07-30T09:58:12.065 - ACQUIRE for NetworkAgentInfo [MOBILE (UNKNOWN) - 100]

bandwidth update requests (by uid):

NetworkStackClient logs:
2026-07-30T09:36:05.251 - Network stack init
2026-07-30T09:36:09.246 - Starting network stack
2026-07-30T09:36:09.247 - Starting network stack process
2026-07-30T09:36:09.248 - Network stack service start requested
2026-07-30T09:36:11.402 - Network stack service connected
2026-07-30T09:36:11.404 - Network stack service registered

pendingNetStackRequests length: 0

Permission Monitor:
Interface filtering rules:








favourtraction@Favours-MacBook-Pro platform-tools % ./adb shell dumpsys telephony.registry | grep -i "mDataConnectionState\|mServiceState"
mServiceState={mVoiceRegState=0(IN_SERVICE), mDataRegState=0(IN_SERVICE), mChannelNumber=2147483647, duplexMode()=0, mCellBandwidths=[], mVoiceOperatorAlphaLong=Airtel Nigeria, mVoiceOperatorAlphaShort=Airtel Nigeria, mDataOperatorAlphaLong=Airtel Nigeria, mDataOperatorAlphaShort=Airtel Nigeria, isManualNetworkSelection=false(automatic), getRilVoiceRadioTechnology=14(LTE), getRilDataRadioTechnology=14(LTE), mCssIndicator=unsupported, mNetworkId=-1, mSystemId=-1, mCdmaRoamingIndicator=-1, mCdmaDefaultRoamingIndicator=-1, mIsEmergencyOnly=false, isUsingCarrierAggregation=false, mLteEarfcnRsrpBoost=0, mNetworkRegistrationInfos=[NetworkRegistrationInfo{ domain=PS transportType=WLAN registrationState=NOT_REG_OR_SEARCHING roamingType=NOT_ROAMING accessNetworkTechnology=IWLAN rejectCause=0 emergencyEnabled=false availableServices=[VOICE,SMS,EMERGENCY] cellIdentity=null voiceSpecificInfo=null dataSpecificInfo=null nrState=NONE}, NetworkRegistrationInfo{ domain=CS transportType=WWAN registrationState=HOME roamingType=NOT_ROAMING accessNetworkTechnology=LTE rejectCause=0 emergencyEnabled=false availableServices=[VOICE,SMS,VIDEO] cellIdentity=CellIdentityLte:{ mCi=3537173 mPci=2147483647 mTac=1020 mEarfcn=2147483647 mBandwidth=2147483647 mMcc=null mMnc=null mAlphaLong= mAlphaShort=} voiceSpecificInfo=VoiceSpecificRegistrationInfo { mCssSupported=false mRoamingIndicator=-1 mSystemIsInPrl=0 mDefaultRoamingIndicator=0} dataSpecificInfo=null nrState=NONE}, NetworkRegistrationInfo{ domain=PS transportType=WWAN registrationState=HOME roamingType=NOT_ROAMING accessNetworkTechnology=LTE rejectCause=-1 emergencyEnabled=false availableServices=[DATA] cellIdentity=CellIdentityLte:{ mCi=2147483647 mPci=2147483647 mTac=2147483647 mEarfcn=2147483647 mBandwidth=2147483647 mMcc=null mMnc=null mAlphaLong= mAlphaShort=} voiceSpecificInfo=null dataSpecificInfo=android.telephony.DataSpecificRegistrationInfo :{ maxDataCalls = 3 isDcNrRestricted = false isNrAvailable = false isEnDcAvailable = false LteVopsSupportInfo :  mVopsSupport = 3 mEmcBearerSupport = 3 mIsUsingCarrierAggregation = false } nrState=NONE}], mNrFrequencyRange=-1, mOperatorAlphaLongRaw=Airtel Nigeria, mOperatorAlphaShortRaw=Airtel Nigeria, mIsIwlanPreferred=false}
mDataConnectionState=2
mServiceState={mVoiceRegState=3(POWER_OFF), mDataRegState=3(POWER_OFF), mChannelNumber=-1, duplexMode()=0, mCellBandwidths=[], mVoiceOperatorAlphaLong=null, mVoiceOperatorAlphaShort=null, mDataOperatorAlphaLong=null, mDataOperatorAlphaShort=null, isManualNetworkSelection=false(automatic), getRilVoiceRadioTechnology=0(Unknown), getRilDataRadioTechnology=0(Unknown), mCssIndicator=unsupported, mNetworkId=-1, mSystemId=-1, mCdmaRoamingIndicator=-1, mCdmaDefaultRoamingIndicator=-1, mIsEmergencyOnly=false, isUsingCarrierAggregation=false, mLteEarfcnRsrpBoost=0, mNetworkRegistrationInfos=[NetworkRegistrationInfo{ domain=CS transportType=WWAN registrationState=UNKNOWN roamingType=NOT_ROAMING accessNetworkTechnology=UNKNOWN rejectCause=0 emergencyEnabled=false availableServices=[] cellIdentity=null voiceSpecificInfo=null dataSpecificInfo=null nrState=NONE}, NetworkRegistrationInfo{ domain=PS transportType=WWAN registrationState=UNKNOWN roamingType=NOT_ROAMING accessNetworkTechnology=UNKNOWN rejectCause=0 emergencyEnabled=false availableServices=[] cellIdentity=null voiceSpecificInfo=null dataSpecificInfo=null nrState=NONE}], mNrFrequencyRange=-1, mOperatorAlphaLongRaw=null, mOperatorAlphaShortRaw=null, mIsIwlanPreferred=false}
mDataConnectionState=0 */
